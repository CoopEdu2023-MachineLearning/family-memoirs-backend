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
    private String avatar_url;

    @Column(name = "avatar_url_new")
    private String avatar_url_new;

    @Column(name = "avatar_status")
    private String avatar_status;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "username_new", unique = true)
    private String username_new;

    @Column(name = "username_status")
    private String username_status;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "introduction_new")
    private String introduction_new;

    @Column(name = "introduction_status")
    private String introduction_status;

    @Column(name = "email", unique = true)
    private String email;

}
