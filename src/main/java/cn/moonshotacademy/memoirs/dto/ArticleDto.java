package cn.moonshotacademy.memoirs.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private int id;

    private String location;

    private String text;

    private int tellerId;

    private int userId;

    private String description;

    private String status;

    private String era;

    private String startDate;

    private String endDate;

    private String descriptionStatus;

    private String textStatus;

}