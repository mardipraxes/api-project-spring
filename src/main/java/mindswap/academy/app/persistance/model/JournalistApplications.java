package mindswap.academy.app.persistance.model;

import javax.persistence.*;

@Entity
@Table(name = "journalist_applications")
public class JournalistApplications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "registration_token")
    private String registrationToken;
}
