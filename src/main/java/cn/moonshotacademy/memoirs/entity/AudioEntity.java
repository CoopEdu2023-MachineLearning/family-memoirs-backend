package cn.moonshotacademy.memoirs.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "audio")
@Data
public class AudioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "audio_url")
    private String audioUrl;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name="name")
    private String name;

    @Column(name="duration")
    private Integer duration;

    @Column(name = "status")
    private Integer status;
}
