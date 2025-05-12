package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.SignUpDto;
import io.jsonwebtoken.JwtException;

public interface UserService {
    ResponseDto<Void> signup(SignUpDto signUpDto) throws JwtException;
}
