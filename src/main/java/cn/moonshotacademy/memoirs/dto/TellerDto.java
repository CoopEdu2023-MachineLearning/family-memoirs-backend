package cn.moonshotacademy.memoirs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TellerDto {
    private Integer id;
    private String name;
    private String intro;
    private String avatarUrlNew;
    private String nameOld;
    private String gender;
    private String birthplace;
    private String birthdate;
    private String introOld;
    private String avatarUrlOld;
    private String identity;
    private String nameNew;
    private String introNew;
    private String avatarNew;
}
