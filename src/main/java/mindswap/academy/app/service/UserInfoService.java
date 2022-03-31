package mindswap.academy.app.service;

import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.PasswordDto;
import mindswap.academy.app.exceptions.InvalidRequestException;
import mindswap.academy.app.exceptions.UserNotFoundException;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoService implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void changePassword(PasswordDto passwordDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("User {} is trying to change his password", username);
        User user = userRepo.findByUsername(username);
        if(user == null) {
            log.warn("User {} not found", username);
            throw new UserNotFoundException(username);
        }



        if(!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            log.warn("Wrong password");
            throw new InvalidRequestException("Wrong password");
        }

        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));

        userRepo.save(user);
    }
}
