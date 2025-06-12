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
@Table(name = "file")
@AllArgsConstructor
@Data
public class FileEntity {

    public FileEntity(String name, String url, String contentType, String delete) {
        fileName = name;
        fileUrl = url;
        fileType = contentType;
        isDeleted = delete;
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

    @Column(name = "is_deleted")
    private String isDeleted;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

}