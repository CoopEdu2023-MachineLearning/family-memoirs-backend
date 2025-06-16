package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teller")
@Data
@NoArgsConstructor
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

    @Column(name = "name_new", unique = true)
    private String nameNew;

    @Column(name = "name_state")
    private String nameState;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthplace")
    private String birthplace;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "intro_old")
    private String introOld;

    @Column(name = "intro_new")
    private String introNew;

    @Column(name = "intro_state")
    private String introState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_id", referencedColumnName = "id")
    private RelationEntity relation;

    @Column(name = "avatar_url_old")
    private String avatarUrlOld;

    @Column(name = "avatar_url_new")
    private String avatarUrlNew;

    @Column(name = "avatar_state")
    private String avatarState;

    @Column(name = "avatar_new")
    private String avatarNew;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teller_id")
    private Set<TellerEntity> teller = new HashSet<>();
}
