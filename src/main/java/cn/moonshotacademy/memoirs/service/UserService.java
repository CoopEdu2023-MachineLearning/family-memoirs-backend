package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.SignUpDto;
import cn.moonshotacademy.memoirs.dto.LoginDto;
import io.jsonwebtoken.JwtException;

public interface UserService {
    String signup(SignUpDto signUpDto) throws JwtException;
    String login(LoginDto loginDto) throws JwtException;
}
