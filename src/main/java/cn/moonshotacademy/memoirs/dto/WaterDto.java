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
public class WaterDto {
    private Long id;
    private String location;
    private String text;
    private String teller; // 仅名字
    private String user;
    private String description;
    private List<ImageDto> images;
    private List<AudioDto> audio;
    private List<TagDto> tags;
    private String textStatus;
    private String descriptionStatus;
    private String era;
    private Integer startYear;
    private Integer endYear;
    private Integer startMonth;
    private Integer endMonth;
}
