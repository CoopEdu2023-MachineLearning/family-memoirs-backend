package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "article")
@Data
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "era")
    private String era;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "location")
    private String location;

    @Column(name = "text")
    private String text;

    @Column(name = "teller_id")
    private Integer teller;

    @Column(name = "user_id")
    private Integer user;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;
}
