package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.AvatarDto;
import cn.moonshotacademy.memoirs.dto.LoginDto;
import cn.moonshotacademy.memoirs.dto.SignUpDto;
import cn.moonshotacademy.memoirs.dto.UserDto;
import io.jsonwebtoken.JwtException;
import java.io.IOException;

public interface UserService {
    String signup(SignUpDto signUpDto) throws JwtException;

    String login(LoginDto loginDto) throws JwtException;

    public void resetPassword(UserDto userDto);

    public UserDto getUserInfo(int id);

    public void changeUserName(int id, UserDto username);

    public void changeUserEmail(int id, String newEmail);

    public void changeUserPassword(int id, String oldPassword, String newPassword);

    public void changeUserAvatar(int id, AvatarDto requestData) throws IOException;
}
