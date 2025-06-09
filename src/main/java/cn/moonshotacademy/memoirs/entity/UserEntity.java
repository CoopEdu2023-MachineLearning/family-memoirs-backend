package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(unique = true)
    private String email;

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

    @Column(name = "photo_address")
    private String photoAddress;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "introduction_new")
    private String introductionNew;

    @Column
    private String name;

    @Column(name = "introduction_status")
    private String introductionStatus;

    public UserEntity(String email, String password, String username) {
        this.password = password;
        this.email = email;
        this.username = username;
    }
}
