package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.EmailDto;

public interface EmailService {
    public void getCode(EmailDto emailDto);

    public void verifyCode(EmailDto emailDto);
}