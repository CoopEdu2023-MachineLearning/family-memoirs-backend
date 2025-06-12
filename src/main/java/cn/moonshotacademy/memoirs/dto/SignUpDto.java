package cn.moonshotacademy.memoirs.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String email;

    private String password;

    private String invitationCode;

    private String verificationCode;
}
