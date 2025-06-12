package cn.moonshotacademy.memoirs.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "relations")
public class RelationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
