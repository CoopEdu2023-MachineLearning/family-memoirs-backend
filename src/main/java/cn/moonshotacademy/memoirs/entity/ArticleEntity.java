package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "location")
    private String location;

    @NonNull
    @Column(name = "text")
    private String text;

    @NonNull
    @Column(name = "teller_id")
    private Integer tellerId;

    @NonNull
    @Column(name = "user_id")
    private Integer userId;

    @NonNull
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "text_status")
    private String textStatus;

    @Column(name = "description_status")
    private String descriptionStatus;

    @Column(name = "era")
    private String era;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "start_month")
    private Integer startMonth;

    @Column(name = "end_month")
    private Integer endMonth;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Set<ImageEntity> images = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Set<AudioEntity> audio = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "article_tag_rel",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tagList = new HashSet<>();
}
