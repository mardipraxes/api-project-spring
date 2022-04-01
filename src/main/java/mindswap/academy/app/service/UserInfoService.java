package mindswap.academy.app.service;

import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.CountryDto;
import mindswap.academy.app.commands.EmailDto;
import mindswap.academy.app.commands.PasswordDto;
import mindswap.academy.app.exceptions.InvalidRequestException;
import mindswap.academy.app.exceptions.UserNotFoundException;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.UserRepo;
import mindswap.academy.app.utils.StringUtil;
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

        if(!StringUtil.isValidPassword(passwordDto.getNewPassword())){
            log.warn("Invalid password");
            throw new InvalidRequestException("Invalid password");
        }

        log.info("User {} is trying to change his password", username);

        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.warn("User {} not found", username);
            throw new UserNotFoundException(username);
        }



        if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            log.warn("Wrong password");
            throw new InvalidRequestException("Wrong password");
        }

        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));

        userRepo.save(user);
    }

    public void changeCountry(CountryDto countryDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("User {} is trying to change his country", username);
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.warn("User {} not found", username);
            throw new UserNotFoundException(username);
        }

        if(user.getCountry().equals(countryDto.getNewCountry())){
            log.warn("Same country... nothing to change");
            throw new InvalidRequestException("Same country... nothing to change");
        }

        if(!passwordEncoder.matches(countryDto.getPassword(), user.getPassword())){
            log.warn("Wrong password");
            throw new InvalidRequestException("Wrong password");
        }

        user.setCountry(countryDto.getNewCountry());
        userRepo.save(user);
    }

    public void changeEmail(EmailDto emailDto) {

        if(!StringUtil.isValidEmail(emailDto.getNewEmail())){
            log.warn("Invalid email");
            throw new InvalidRequestException("Invalid email");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("User {} is trying to change his email", username);
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.warn("User {} not found", username);
            throw new UserNotFoundException(username);
        }

        if(!user.getEmail().equals(emailDto.getEmail())){
            log.warn("Wrong email");
            throw new InvalidRequestException("Wrong email");
        }

        if(!passwordEncoder.matches(emailDto.getPassword(), user.getPassword())){
            log.warn("Wrong password");
            throw new InvalidRequestException("Wrong password");
        }

        user.setEmail(emailDto.getNewEmail());
        userRepo.save(user);
    }

}
