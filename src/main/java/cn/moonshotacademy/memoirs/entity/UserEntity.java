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

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "email", unique = true)
    private String email;

    public UserEntity(String avatar_url, String email, String password, String username) {
        this.avatar_url = avatar_url;
        this.password = password;
        this.email = email;
        this.username = username;
    }
}
