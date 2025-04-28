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
    private String era;
    private Date startDate;
    private Date endDate;
    private String text;
    //    private String teller;
    private UserDto user;
    private String description;
    private Integer status;
}