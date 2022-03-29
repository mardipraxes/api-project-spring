package mindswap.academy.app.controller;

import mindswap.academy.app.commands.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
public class LoginController {



    @PostMapping("login")
    public ResponseEntity<?> login(LoginRequest loginRequest) {



        return null;
    }


}
