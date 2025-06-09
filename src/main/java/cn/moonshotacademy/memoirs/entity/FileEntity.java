package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "files")
@AllArgsConstructor
@Data
public class FileEntity {

    public FileEntity(String name, String url, String contentType) {
        fileName = name;
        fileUrl = url;
        fileType = contentType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

}