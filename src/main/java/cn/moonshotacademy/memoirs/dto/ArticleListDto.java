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
public class ArticleListDto {
    private int id;
    private String location;
    private Integer startYear;
    private Integer startMonth;
    private String era;
    private List<TagDto> tags;
}