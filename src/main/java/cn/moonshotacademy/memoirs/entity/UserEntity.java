package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.*;
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
    private String avatar_url;

    @Column(name = "avatar_url_new")
    private String avatar_url_new;
    @Column(unique = true)
    private String email;

    @Column(name = "avatar_status")
    private String avatar_status;

    @Column(name = "username", unique = true)
    @Column(unique = true)
    private String username;

    @Column(name = "username_new", unique = true)
    private String username_new;

    @Column(name = "username_status")
    private String username_status;

    @Column(name = "password", unique = true)
    @Column
    private String password;

    @Column(name = "introduction")

    @Column(name = "photo_address")
    private String photoAddress;

    @Column
    private String introduction;

    @Column(name = "introduction_new")
    private String introduction_new;

    @Column
    private String name;

    @Column(name = "introduction_status")
    private String introduction_status;

    @Column(name = "email", unique = true)
    private String email;

    public UserEntity(String email, String password, String username) {
        this.password = password;
        this.email = email;
        this.username = username;
    }
}
