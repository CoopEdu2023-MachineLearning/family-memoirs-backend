package cn.moonshotacademy.memoirs.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class TellerDto {
    private String identity;
    private String nameNew;
    private String gender; 
    private String birthplace;
    private LocalDate birthdate;
    private String introNew;
    private String avatarNew;
}