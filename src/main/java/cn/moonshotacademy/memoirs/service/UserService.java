package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.UserDto;

public interface UserService {

    public UserDto getUserInfo(int id);

    public void changeUserName(int id, UserDto username);

    public void changeUserEmail(String newEmail, String email);

    public void changeUserPassword(String oldPassword, String newPassword, String email);

    public void changeUserAvatar(String avatarUrl, String email);
}
