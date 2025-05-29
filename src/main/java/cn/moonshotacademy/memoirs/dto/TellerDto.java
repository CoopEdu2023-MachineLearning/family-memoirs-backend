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
    private String nameOld;
    private String gender;
    private String birthplace;
    private String birthdate;
    private String introOld;
    private String avatarUrlOld;
}