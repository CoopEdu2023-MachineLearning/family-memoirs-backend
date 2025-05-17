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
    private String gender;
    private String birthplace;
    private String birthdate;
    private String intro;
    private String avatar_url;
}
