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

    @NonNull
    @Column(name = "era")
    private Integer era;

    @NonNull
    @Column(name = "start_year")
    private Integer startYear;

    @NonNull
    @Column(name = "end_year")
    private Integer endYear;

    @NonNull
    @Column(name = "start_month")
    private Integer startMonth;

    @NonNull
    @Column(name = "end_month")
    private Integer endMonth;

    @Column(name = "description_status")
    private String descriptionStatus;

    @Column(name = "text_status")
    private String textStatus;

}
