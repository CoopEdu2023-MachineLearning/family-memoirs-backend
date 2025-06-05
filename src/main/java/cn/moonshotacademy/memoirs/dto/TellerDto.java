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
public class TellerDto {
    private Integer id;
    private String name;
    private String intro;
    private String avatar_url;
    private String nameOld;
    private String gender;
    private String birthplace;
    private Date birthdate;
    private String introOld;
    private String avatarUrlOld;
    private String identity;
    private String nameNew;
    private String introNew;
    private String avatarNew;
}
