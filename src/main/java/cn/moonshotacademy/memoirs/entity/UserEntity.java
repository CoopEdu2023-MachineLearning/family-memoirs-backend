package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "avatar_url_new")
    private String avatarUrlNew;

    @Column(name = "avatar_status")
    private String avatarStatus;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "username_new", unique = true)
    private String usernameNew;

    @Column(name = "username_status")
    private String usernameStatus;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "introduction_new")
    private String introductionNew;

    @Column(name = "introduction_status")
    private String introductionStatus;

    @Column(name = "email", unique = true)
    private String email;

}
