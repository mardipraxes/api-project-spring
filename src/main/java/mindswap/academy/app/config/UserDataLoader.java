package mindswap.academy.app.config;

import mindswap.academy.app.persistance.model.*;
import mindswap.academy.app.persistance.repository.NewsRepo;
import mindswap.academy.app.persistance.repository.RoleRepo;
import mindswap.academy.app.persistance.repository.UserRepo;
import mindswap.academy.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.stream.IntStream;

@Component
public class UserDataLoader {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private NewsRepo newsRepo;


    public void loadData() {
        String[] names = StringUtil.getNamesArray();

        Role roleAdmin = new Role("ROLE_Admin");
        Role roleJournalist = new Role("ROLE_Journalist");
        Role roleUser = new Role("ROLE_User");

        Rating rating = Rating.builder()
                .truthfulness(1)
                .biasedRating(1)
                .writingQuality(1)
                .build();

        NewsPost newsPost = NewsPost.builder()
                .rating(null)
                .categories(null)
                .content("Russia killed levensky")
                .title("Russia killed levensky")
                .build();


        createRoleIfNotFound(roleAdmin);
        createRoleIfNotFound(roleJournalist);
        createRoleIfNotFound(roleUser);
        createNewsIfNotFound(newsPost);

        IntStream.range(0, 15).forEach(i -> {
           User user = User.builder()
                    .username(names[i].toLowerCase() + ((int) (Math.random() * 100)))
                    .email(names[i].concat(String.valueOf((int) (Math.random() * 40) + 1960)).toLowerCase() + "@gmail.com")
                    .password("123456")
                    .country("USA").build();

           user.setRoles(new HashSet<>());
           user.getRoles().add(roleRepo.findByName(roleUser.getName()));
           userRepo.save(user);
        });

        IntStream.range(16, 20).forEach(i -> {
            Journalist journalist = new Journalist();
            journalist.setUsername(names[i].toLowerCase() + ((int) (Math.random() * 100)));
            journalist.setEmail(names[i].concat(String.valueOf((int) (Math.random() * 40) + 1960)).toLowerCase() + "@gmail.com");
            journalist.setPassword("123456");
            journalist.setCountry("USA");
            journalist.setNewsPosts(new HashSet<>());
            journalist.setRoles(new HashSet<>());
            journalist.getRoles().add(roleRepo.findByName(roleJournalist.getName()));
            userRepo.save(journalist);
        });
    }

    @Transient
    private void createNewsIfNotFound(NewsPost newsPost) {
        NewsPost newsPost1 = newsRepo.findByTitle(newsPost.getTitle());
        if (newsPost1 == null) {
            newsRepo.save(newsPost);
        }
    }

    @Transient
    private void createRoleIfNotFound(Role roleAdmin) {
        if (roleRepo.findByName(roleAdmin.getName()) == null) {
            roleRepo.save(roleAdmin);
        }
    }
}
