package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "article")
@Data
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "text")
    private String text;

    @Column(name = "teller_id")
    private Long tellerId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "description")
    private String description;

    @Column(name = "text_status")
    private Integer textStatus;

    @Column(name = "description_status")
    private Integer descriptionStatus;

    @Column(name = "era")
    private Integer era;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "start_month")
    private Integer startMonth;

    @Column(name = "end_month")
    private Integer endMonth;
}
