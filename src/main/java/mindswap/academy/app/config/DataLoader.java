package mindswap.academy.app.config;

import mindswap.academy.app.persistance.model.*;
import mindswap.academy.app.persistance.repository.*;
import mindswap.academy.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.IntStream;

@Component
public class DataLoader {

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


    public void loadUserData() {
        String[] names = StringUtil.getNamesArray();

        IntStream.range(0, 15).forEach(i -> {
           User user = User.builder()
                    .username(names[i].toLowerCase() + ((int) (Math.random() * 100)))
                    .email(names[i].concat(String.valueOf((int) (Math.random() * 40) + 1960)).toLowerCase() + "@gmail.com")
                    .password(passwordEncoder.encode("123456"))
                    .country("USA").build();

//           user.setRoles(new HashSet<>());
//           user.getRoles().add(roleRepo.findByName(roleUser.getName()));
           userRepo.save(user);
        });

        IntStream.range(16, 20).forEach(i -> {
            Journalist journalist = new Journalist();
            journalist.setUsername(names[i].toLowerCase() + ((int) (Math.random() * 100)));
            journalist.setEmail(names[i].concat(String.valueOf((int) (Math.random() * 40) + 1960)).toLowerCase() + "@journalist.com");
            journalist.setPassword("123456");
            journalist.setCountry("USA");
//            journalist.setNewsPosts(new HashSet<>());
//            journalist.getNewsPosts().add(newsRepo.findByTitle("Russia killed levensky").get());
//            journalist.setRoles(new HashSet<>());
//            journalist.getRoles().add(roleRepo.findByName(roleJournalist.getName()));
            userRepo.save(journalist);
        });

        Role roleAdmin = new Role("ROLE_Admin");
        Role roleJournalist = new Role("ROLE_Journalist");
        Role roleUser = new Role("ROLE_User");

        createRoleIfNotFound(roleAdmin);
        createRoleIfNotFound(roleJournalist);
        createRoleIfNotFound(roleUser);

        userRepo.findAll().forEach(user -> {
            user.setRoles(new HashSet<>());
            if(user instanceof Journalist) {
                user.getRoles().add(roleJournalist);
                user.getRoles().add(roleUser);
                userRepo.save(user);
            } else {
                user.getRoles().add(roleRepo.findByName(roleUser.getName()));
                userRepo.save(user);
            }

        });

    }


    public void loadNewsData() {
        String[] titles = StringUtil.getNewsTitlesArray();
        String[] content = StringUtil.getNewsContentArray();

        IntStream.range(0, 4).forEach(i -> {
            NewsPost news = NewsPost.builder()
                    .title(titles[i])
                    .content(content[i])
                    .titleURL(titles[i].replaceAll(" ", "_").toLowerCase().toLowerCase())
                    .publishedDate(new Date())
                    .imageURL("https://picsum.photos/200/300?image=".concat(String.valueOf(i)))
                    .build();

            createNewsIfNotFound(news);
        });

        String [] categories = StringUtil.getCategoriesArray();


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
//        NewsPost newsPost1 = newsRepo.findByTitle(newsPost.getTitle()).get();
        newsRepo.save(newsPost);

    }

    @Transient
    private void createRoleIfNotFound(Role roleAdmin) {
        if (roleRepo.findByName(roleAdmin.getName()) == null) {
            roleRepo.save(roleAdmin);
        }
    }
}
