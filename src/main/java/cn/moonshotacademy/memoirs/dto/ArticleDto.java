package cn.moonshotacademy.memoirs.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private Integer id;

    private String location;

    private String text;

    private Integer tellerId;

    private Integer userId;

    private String description;

    private String status;

    private Integer era;

    private Integer startYear;

    private Integer endYear;

    private Integer startMonth;

    private Integer endMonth;

    private String descriptionStatus;

    private String textStatus;

}