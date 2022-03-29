package mindswap.academy.app.controller;

import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.service.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

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



}
