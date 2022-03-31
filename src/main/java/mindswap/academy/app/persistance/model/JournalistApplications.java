package mindswap.academy.app.persistance.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
