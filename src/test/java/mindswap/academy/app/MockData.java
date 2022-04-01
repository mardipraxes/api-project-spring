package mindswap.academy.app;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.commands.UserDto;
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
                .password("test")
                .email("test@test.com")
                .confirmPassword("test")
                .country("test")
                .build();
    }


}
