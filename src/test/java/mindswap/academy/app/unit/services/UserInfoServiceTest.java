package mindswap.academy.app.unit.services;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.CountryDto;
import mindswap.academy.app.commands.EmailDto;
import mindswap.academy.app.commands.PasswordDto;
import mindswap.academy.app.exceptions.InvalidRequestException;
import mindswap.academy.app.exceptions.UserNotFoundException;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.UserRepo;
import mindswap.academy.app.service.UserInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserInfoServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @Mock
    private Authentication authentication;

    private UserInfoService userInfoService;

    @BeforeEach
    public void setup() {
        userInfoService = new UserInfoService(userRepo, passwordEncoder);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void changePassword() {
        //GIVEN
        User user = MockData.getMockUser();
        PasswordDto passwordDto = MockData.getMockPasswordDto();
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("test");
        when(userRepo.findByUsername(any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(passwordEncoder.encode(any())).thenReturn("test1");
        when(userRepo.save(any())).thenReturn(user);

        //WHEN
        userInfoService.changePassword(passwordDto);
        //THEN
        assertEquals("test1", user.getPassword());
        verify(userRepo).save(user);
        verify(passwordEncoder).encode(any());
        verify(passwordEncoder).matches(any(), any());
        verify(userRepo).findByUsername(any());
    }
    @Test
    void changeCountry() {
        //GIVEN
        User user = MockData.getMockUser();
        CountryDto countryDto = MockData.getMockCountryDto();
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("test");
        when(userRepo.findByUsername(any())).thenReturn(user);
        when(userRepo.save(any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        //WHEN
       userInfoService.changeCountry(countryDto);
        //THEN
        assertEquals("Portugal", user.getCountry());
        verify(userRepo).save(user);
        verify(userRepo).findByUsername(any());
    }

    @Test
    void changeEmail() {
        //GIVEN
        User user = MockData.getMockUser();
        EmailDto emailDto = MockData.getMockEmailDto();
        user.setEmail("john@gmail.com");
        emailDto.setEmail("john@gmail.com");
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("test");
        when(userRepo.findByUsername(any())).thenReturn(user);
        when(userRepo.save(any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        //WHEN
        userInfoService.changeEmail(emailDto);
        //THEN
        assertEquals("joao@hotmail.com", user.getEmail());
        verify(userRepo).save(user);
        verify(userRepo).findByUsername(any());
    }

    @Test
    void changeEmailButItsWrong() {
        //GIVEN
        User user = MockData.getMockUser();
        EmailDto emailDto = MockData.getMockEmailDto();
        user.setEmail("john1@gmail.com");
        emailDto.setEmail("john@gmail.com");
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("test");
        when(userRepo.findByUsername(any())).thenReturn(user);
        //WHEN

        //THEN
        assertThrows(InvalidRequestException.class, () -> userInfoService.changeEmail(emailDto));
    }

    @Test
    void changePasswordUserIsNull() {
        //GIVEN
        User user = MockData.getMockUser();
        PasswordDto passwordDto = MockData.getMockPasswordDto();
        //WHEN
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("test");
        when(userRepo.findByUsername(any())).thenReturn(null);
        //THEN
        assertThrows(UserNotFoundException.class, () -> userInfoService.changePassword(passwordDto));
    }
}