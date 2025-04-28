package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.SignUpDto;
import io.jsonwebtoken.JwtException;

public interface UserService {
    String signup(SignUpDto signUpDto) throws JwtException;
}
