package cn.moonshotacademy.memoirs.service;

public interface EmailService {
    public void getCode(String email);

    public void verifyCode(String email, String code);
}