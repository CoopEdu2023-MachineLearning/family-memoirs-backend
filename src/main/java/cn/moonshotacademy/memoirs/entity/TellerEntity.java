package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "teller")
@Data
@NoArgsConstructor
public class TellerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "name_new", unique = true)
    private String name_new;

    @Column(name="name_state")
    private String name_state;

    @Column(name="gender")
    private Boolean gender;

    @Column(name="birthplace")
    private String birthplace;

    @Column(name="birthdate")
    private Date birthdate;

    @Column(name="intro")
    private String intro;

    @Column(name="intro_new")
    private String intro_new;

    @Column(name="intro_state")
    private String intro_state;

    @Column(name="avatar_url")
    private String avatar_url;

    @Column(name="avatar_url_new")
    private String avatar_url_new;

    @Column(name="avatar_state")
    private String avatar_state;
}
