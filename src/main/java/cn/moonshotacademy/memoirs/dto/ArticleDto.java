package cn.moonshotacademy.memoirs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String location;
    private String text;
    private Integer tellerId;
    private UserDto user;
    private String description;
    private Integer textStatus;
    private Integer descriptionStatus;
    private Integer era;
    private Integer startYear;
    private Integer endYear;
    private Integer startMonth;
    private Integer endMonth;
}