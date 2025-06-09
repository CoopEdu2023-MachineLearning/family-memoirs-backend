package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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