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
    private String gender; // 改为String类型，与实体类保持一致
    private String birthplace;
    private Date birthdate;
    private String intro;
    private String avatar_url;
}
