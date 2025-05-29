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

    @Column(name="identity")
    private String identity;

    @Column(name = "name_old", unique = true)
    private String nameOld;

    @Column(name = "name_new", unique = true)
    private String nameNew;

    @Column(name="name_state")
    private String nameState;

    @Column(name="gender")
    private String gender;

    @Column(name="birthplace")
    private String birthplace;

    @Column(name="birthdate")
    private String birthdate;

    @Column(name="intro_old")
    private String introOld;

    @Column(name="intro_new")
    private String introNew;

    @Column(name="intro_state")
    private String introState;

    @Column(name="avatar_url_old")
    private String avatarUrlOld;

    @Column(name="avatar_url_new")
    private String avatarUrlNew;

    @Column(name="avatar_state")
    private String avatarState;
}