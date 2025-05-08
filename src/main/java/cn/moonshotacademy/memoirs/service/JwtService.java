package cn.moonshotacademy.memoirs.service;

import io.jsonwebtoken.JwtException;

public interface JwtService {

    String setToken(Integer id);

    String decodeJwt(String token) throws JwtException;
}
