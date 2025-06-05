package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.UserDto;

public interface UserService {

    public UserDto getUserInfo(int id);

    public void changeUserName(int id, UserDto username);

    public void changeUserEmail(int id, String newEmail);

    public void changeUserPassword(int id, String oldPassword, String newPassword);

    public void changeUserAvatar(int id, String avatarUrl);
}
