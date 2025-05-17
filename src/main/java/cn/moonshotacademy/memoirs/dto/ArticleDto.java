package cn.moonshotacademy.memoirs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String location;
    private String text;
    private TellerDto teller;
    private String user;
    private String description;
    private List<ImageDto> images;
    private List<TagDto> tags;
    private Integer textStatus;
    private Integer descriptionStatus;
    private Integer era;
    private Integer startYear;
    private Integer endYear;
    private Integer startMonth;
    private Integer endMonth;
}