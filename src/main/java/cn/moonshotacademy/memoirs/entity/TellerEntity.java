package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teller")
public class TellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_old")
    private String nameOld;

    @Column(name = "name_new")
    private String nameNew;

    @Column(name = "name_state")
    private String nameState;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthplace")
    private String birthPlace;

    @Column(name = "birthdate")
    private String birthDate;

    @Column(name = "intro_old")
    private String introOld;

    @Column(name = "intro_new")
    private String introNew;

    @Column(name = "intro_state")
    private String introState;

}
