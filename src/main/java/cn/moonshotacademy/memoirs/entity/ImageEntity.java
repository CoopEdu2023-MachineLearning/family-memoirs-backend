package cn.moonshotacademy.memoirs.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "image")
@Data
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "article_id")
    private Long articleId;
}
