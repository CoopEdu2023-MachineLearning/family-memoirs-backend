package cn.moonshotacademy.memoirs.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
