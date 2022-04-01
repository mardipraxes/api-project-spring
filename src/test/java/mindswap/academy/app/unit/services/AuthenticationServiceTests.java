package mindswap.academy.app.unit.services;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.JournalistApplicationDto;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.InvalidRequestException;
import mindswap.academy.app.exceptions.UserAlreadyExistsException;
import mindswap.academy.app.persistance.model.JournalistApplications;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.repository.JournalistApplicationsRepo;
import mindswap.academy.app.persistance.repository.UserRepo;
import mindswap.academy.app.service.AuthenticationService;
import mindswap.academy.app.utils.MHasher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTests {
    @Mock
    private  UserRepo userRepo;
    @Mock
    private  UserConverter userConverter;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  JournalistApplicationsRepo journalistApplicationsRepo;
    @Mock
    private MHasher mHasher;

    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        this.authenticationService = new AuthenticationService(userRepo, userConverter, passwordEncoder, journalistApplicationsRepo, mHasher);
    }

    @Test
    public void testRegisterUser() {
        // Given
        RegistrationDto registrationDto = MockData.getMockRegistrationDto();
        when(userRepo.findByUsername(any())).thenReturn(null);
        when(journalistApplicationsRepo.findByUsername(any())).thenReturn(null);
        when(passwordEncoder.encode(any())).thenReturn(MockData.getMockUser().getPassword());
        when(userConverter.toEntityFromRegistrationDto(any(),any())).thenReturn(MockData.getMockUser());
        when(userRepo.save(any())).thenReturn(MockData.getMockUser());
        // When
         authenticationService.registerUser(registrationDto);

         //Then
        verify(userRepo).findByUsername(any());
        verify(journalistApplicationsRepo).findByUsername(any());
        verify(passwordEncoder).encode(any());
        verify(userConverter).toEntityFromRegistrationDto(any(),any());
        verify(userRepo).save(any());

    }

    @Test
    public void testRegisterUserButItAlreadyExists() {
        // Given
        RegistrationDto registrationDto = MockData.getMockRegistrationDto();
        //When
        when(userRepo.findByUsername(any())).thenReturn(MockData.getMockUser());
        // Then«
        assertThrows(UserAlreadyExistsException.class, () -> authenticationService.registerUser(registrationDto));

    }
    @Test
    public void testRegisterUserButItPasswordOnlyHasNumbers() {
        // Given
        RegistrationDto registrationDto = MockData.getMockRegistrationDto();
        registrationDto.setPassword("123456789");
        assertThrows(InvalidRequestException.class, () -> authenticationService.registerUser(registrationDto));

    }
    @Test
    public void testRegisterUserButItPasswordIsOnlyLowercase() {
        // Given
        RegistrationDto registrationDto = MockData.getMockRegistrationDto();
        registrationDto.setPassword("abcdefgh");
        assertThrows(InvalidRequestException.class, () -> authenticationService.registerUser(registrationDto));

    }
    @Test
    public void testRegisterUserButItPasswordIsNotLongEnough() {
        // Given
        RegistrationDto registrationDto = MockData.getMockRegistrationDto();
        registrationDto.setPassword("1!Aa");
        assertThrows(InvalidRequestException.class, () -> authenticationService.registerUser(registrationDto));

    }
    @Test
    public void testRegisterUserButEmailIsNotValid() {
        // Given
        RegistrationDto registrationDto = MockData.getMockRegistrationDto();
        registrationDto.setEmail("john1996gmail.com");

        //THEN
        assertThrows(InvalidRequestException.class, () -> authenticationService.registerUser(registrationDto));

    }

    @Test
    public void testGetGrantedAuthorities() {
        // Given
        Set<Role> roles = Set.of(MockData.getMockRole());

        // When
       Collection<? extends GrantedAuthority> authorities = authenticationService.getAuthorities(roles);

       // Then
       assertEquals(1, authorities.size());
       assertInstanceOf(SimpleGrantedAuthority.class, authorities.iterator().next());
       assertEquals("ROLE_User", authorities.iterator().next().getAuthority());
    }

    @Test
    public void testApplyJournalist() {
        // Given
        String expectedToken = "testToken";
        JournalistApplicationDto journalistApplicationDto = MockData.getMockJournalistApplicationDto();
        JournalistApplications journalistApplication = MockData.getMockJournalistApplications();
        when(userRepo.findByUsername(any())).thenReturn(null);
        when(userRepo.findByEmail(any())).thenReturn(null);
        when(mHasher.getToken(any())).thenReturn(expectedToken);
        // When
        String token = authenticationService.applyJournalist(journalistApplicationDto);

        // Then
        verify(journalistApplicationsRepo).save(any());
        assertEquals(expectedToken, token);

    }

    @Test
    public void testApplyJournalistButUsernameExistsAlready() {
        // Given
        String expectedToken = "testToken";
        JournalistApplicationDto journalistApplicationDto = MockData.getMockJournalistApplicationDto();
        JournalistApplications journalistApplication = MockData.getMockJournalistApplications();
        when(userRepo.findByUsername(any())).thenReturn(MockData.getMockUser());
        // When


        // Then
        assertThrows(UserAlreadyExistsException.class, () -> authenticationService.applyJournalist(journalistApplicationDto));

    }
}
