package mindswap.academy.app;

import mindswap.academy.app.commands.*;
import mindswap.academy.app.persistance.model.*;

import java.util.List;

public class MockData {


    public static User getMockUser() {
        return User.builder()
                .username("test")
                .password("test")
                .email("test@test.com")
                .country("test")
                .id(2L)
                .roles(null)
                .build();
    }

    public static NewsPost getMockNewsPost() {
        return NewsPost.builder()
                .title("test")
                .content("test")
                .journalist(getMockJournalist())
                .categories(List.of(getMockCategory()))
                .id(2L)
                .build();
    }

    public static UserDto getMockUserDto() {
        return UserDto.builder()
                .username("test")
                .country("test")
                .email("test@test.com")
                .build();
    }

    public static NewsPostDto getMockNewsPostDto() {
        return NewsPostDto.builder()
                .title("test")
                .content("test")
                .build();
    }

    public static Journalist getMockJournalist() {
        Journalist journalist = new Journalist();
        journalist.setId(2L);
        journalist.setUsername("test");
        journalist.setPassword("test");
        journalist.setEmail("john@journalist@email.com");
        journalist.setCountry("test");
        return journalist;

    }

    public static Category getMockCategory() {
        return Category.builder()
                .name("test")
                .id(2L)
                .build();
    }

    public static Rating getMockRating() {
        return Rating.builder()
                .biasedRating(2)
                .writingQuality(2)
                .truthfulness(2)
                .counter(0)
                .id(2L)
                .build();
    }

    public static RegistrationDto getMockRegistrationDto() {
        return RegistrationDto.builder()
                .username("test")
                .password("test123!A")
                .email("test@test.com")
                .confirmPassword("test")
                .country("test")
                .build();
    }

    public static PasswordDto getMockPasswordDto() {
        return PasswordDto.builder()
                .oldPassword("test")
                .newPassword("test123A!")
                .confirmPassword("test1")
                .build();
    }
    public static CountryDto getMockCountryDto() {
        return CountryDto.builder()
                .password("test")
                .newCountry("Portugal")
                .build();
    }

    public static EmailDto getMockEmailDto() {
        return EmailDto.builder()
                .password("test")
                .email("joao123@hotmail.com")
                .newEmail("joao@hotmail.com")
                .build();
    }

    public static Role getMockRole() {
        return Role.builder()
                .name("ROLE_User")
                .build();
    }

    public static JournalistApplications getMockJournalistApplications() {
        return JournalistApplications.builder()
                .id(2L)
                .username("test")
                .registrationToken("test")
                .build();
    }

    public static JournalistApplicationDto getMockJournalistApplicationDto() {
        return JournalistApplicationDto.builder()
                .username("test")
                .country("test")
                .email("john@gmail.com")
                .build();
    }

    public static RatingDto getMockRatingDto() {
        return RatingDto.builder()
                .biasedRating(2)
                .writingQuality(2)
                .truthfulness(2)
                .build();
    }
}
