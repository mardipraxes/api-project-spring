package mindswap.academy.app.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.*;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.service.AuthenticationService;
import mindswap.academy.app.service.UserInfoService;
import mindswap.academy.app.service.UserServiceImpl;
import mindswap.academy.app.utils.AlgorithmUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/users")
    private ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = userServiceImpl.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<UserDto> getUserById(@PathVariable Long id) {

        UserDto user = userServiceImpl.getUserById(id);

        return ResponseEntity.ok(user);
    }


    @PostMapping("/register")
    private ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationDto registrationDto) {

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        log.info("Registering user: {}", registrationDto.getUsername());

        authenticationService.registerUser(registrationDto);

        return ResponseEntity.ok("Successfully registered");
    }

    @GetMapping("/me")
    private ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.ok(userServiceImpl.getCurrentUser());
    }

    @PatchMapping("/change-password")
    private ResponseEntity<?> changePassword(@RequestBody PasswordDto passwordDto) {

        userInfoService.changePassword(passwordDto);

        return ResponseEntity.ok("Successfully changed password");
    }

    @PatchMapping("/change-country")
    private ResponseEntity<?> changeCountry(@RequestBody CountryDto countryDto) {
        userInfoService.changeCountry(countryDto);
        return ResponseEntity.ok("Successfully changed country");

    }

    @PatchMapping("/change-email")
    private ResponseEntity<?> changeCountry(@RequestBody EmailDto emailDto) {
        userInfoService.changeEmail(emailDto);
        return ResponseEntity.ok("Successfully changed email");
    }

    @GetMapping("/logout")
    private ResponseEntity<String> logout() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return ResponseEntity.badRequest().body("User is not logged in");
        }

        authenticationService.logout();

        return ResponseEntity.ok("Successfully logged out");
    }

    @PostMapping("/apply-journalist")
    private ResponseEntity<String> applyJournalist(@Valid @RequestBody JournalistApplicationDto journalistApplicationDto) {
        String token = authenticationService.applyJournalist(journalistApplicationDto);
        return ResponseEntity.ok("Successfully applied for journalist, you can use this token to register: " + token);
    }

    @GetMapping("/refresh-token")
    private void refreshToken(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        log.info("Authorization header: {}", authorizationHeader);

        if(authorizationHeader == null ){
            response.setStatus(FORBIDDEN.value());
            response.setContentType(APPLICATION_JSON.toString());
            response.getWriter().write("{\"message\": \"No token provided\"}");
            return;
        }

        if(authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                JWTVerifier verifier = JWT.require(AlgorithmUtil.getAlgorithm()).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userServiceImpl.getUserByUsername(username);
                Algorithm algorithm = AlgorithmUtil.getAlgorithm();
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 90 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                String refreshToken = JWT.create()
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                Map<String , String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getWriter(), tokens);
            } catch (Exception e) {

                log.error("Error refreshing token " + e.getMessage());
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error", e.getMessage());
                response.setContentType(APPLICATION_JSON.toString());
                new ObjectMapper().writeValue(response.getOutputStream(), error);

            }
        }
    }
}






