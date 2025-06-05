package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
@Entity
@Table(name = "teller")
@Data
public class TellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "identity")
    private String identity;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "name_old")
    private String nameOld;

    @Column(name = "name_new")
    private String nameNew;

    @Column(name = "name_state")
    private String nameState;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthplace")
    private String birthplace;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "intro_old")
    private String introOld;

    @Column(name = "intro_new")
    private String introNew;

    @Column(name = "intro_state")
    private String introState;

    @Column(name = "avatar_old")
    private String avatarOld;

    @Column(name = "avatar_new")
    private String avatarNew;

    @Column(name = "avatar_state")
    private String avatarState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_id", referencedColumnName = "id")
    private RelationEntity relation;

}