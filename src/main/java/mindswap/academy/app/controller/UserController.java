package mindswap.academy.app.controller;

import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.*;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.service.AuthenticationService;
import mindswap.academy.app.service.UserInfoService;
import mindswap.academy.app.service.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

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

        if(!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
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
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            return ResponseEntity.badRequest().body("User is not logged in");
        }

        authenticationService.logout();

        return ResponseEntity.ok("Successfully logged out");
    }

    @GetMapping("/refresh-token")
    private ResponseEntity<String> refreshSecurityToken() {
        return ResponseEntity.ok("Successfully logged in");
    }
}




