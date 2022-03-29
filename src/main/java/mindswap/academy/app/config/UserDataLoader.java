package mindswap.academy.app.config;

import mindswap.academy.app.persistance.model.*;
import mindswap.academy.app.persistance.repository.*;
import mindswap.academy.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UserDataLoader {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepo categoryRepo;


    public void loadData() {
        String[] names = StringUtil.getNamesArray();

        Role roleAdmin = new Role("ROLE_Admin");
        Role roleJournalist = new Role("ROLE_Journalist");
        Role roleUser = new Role("ROLE_User");

        Category category = Category.builder()
                .newsPost(null)
                .description("Russia")
                .name("Russia")
                .build();

        createCategoryIfNotFound(category);

        Rating rating = Rating.builder()
                .truthfulness(1)
                .biasedRating(1)
                .writingQuality(1)
                .build();

        createRatingIfNotFound(rating);


        createRoleIfNotFound(roleAdmin);
        createRoleIfNotFound(roleJournalist);
        createRoleIfNotFound(roleUser);


        IntStream.range(0, 15).forEach(i -> {
           User user = User.builder()
                    .username(names[i].toLowerCase() + ((int) (Math.random() * 100)))
                    .email(names[i].concat(String.valueOf((int) (Math.random() * 40) + 1960)).toLowerCase() + "@gmail.com")
                    .password(passwordEncoder.encode("123456"))
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
            journalist.getNewsPosts().add(newsRepo.findByTitle("Russia killed levensky").get());
            journalist.setRoles(new HashSet<>());
            journalist.getRoles().add(roleRepo.findByName(roleJournalist.getName()));
            userRepo.save(journalist);
        });

        List<Journalist> journalists = new ArrayList<>();
        for(User user : userRepo.findAll()) {
            if(user instanceof Journalist) {
                journalists.add((Journalist) user);
            }
        }

        NewsPost newsPost = NewsPost.builder()
                .journalist(journalists.get(0))
                .rating(ratingRepo.findAll().get(0))
                .categories(new HashSet<>(categoryRepo.findAll()))
                .content("Russia killed levensky")
                .title("Russia killed levensky")
                .build();
        createNewsIfNotFound(newsPost);
        Rating rating1 = ratingRepo.findById(1L).get();
        rating1.setNews(newsRepo.findById(1L).get());
        ratingRepo.save(rating1);
    }

    private void createCategoryIfNotFound(Category category) {
        Category category1 = categoryRepo.findByName(category.getName());
        if (category1 == null) {
            categoryRepo.save(category);
        }
    }

    private void createRatingIfNotFound(Rating rating) {
        Rating rating1 = ratingRepo.findByTruthfulness(rating.getTruthfulness());
        if (rating1 == null) {
            ratingRepo.save(rating);
        }
    }



    @Transient
    private void createNewsIfNotFound(NewsPost newsPost) {
        NewsPost newsPost1 = newsRepo.findByTitle(newsPost.getTitle()).get();
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
