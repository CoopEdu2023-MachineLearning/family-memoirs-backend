package cn.moonshotacademy.memoirs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String avatarUrl;
    private String introduction;
    private String avatarUrlNew;
    private String avatarUrlStatus;
    private String usernameNew;
    private String usernameStatus;
    private String introductionNew;
    private String introductionStatus;
}