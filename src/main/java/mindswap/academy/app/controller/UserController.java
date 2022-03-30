package mindswap.academy.app.controller;

import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.service.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

//    @PreAuthorize("hasRole('Admin')")
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

        return ResponseEntity.ok(new RedirectView("/login"));
    }



}
