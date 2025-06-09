package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.AvatarDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.UserDto;
import cn.moonshotacademy.memoirs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseDto<UserDto> getUserInfo(@PathVariable int id) {
        return ResponseDto.success(userService.getUserInfo(id));
    }

    @PatchMapping("/{id}/username")
    public ResponseDto<Void> changeUsername(@PathVariable int id, @RequestBody UserDto username) {
        userService.changeUserName(id, username);
        return ResponseDto.success();
    }

    @PatchMapping("/{id}/password")
    public ResponseDto<Void> changeUserPassword(@PathVariable int id, @RequestBody java.util.Map<String, String> passwordData) {
        userService.changeUserPassword(id,
                passwordData.get("oldPassword"),
                passwordData.get("newPassword"));
        return ResponseDto.success();
    }

    @PatchMapping("/{id}/avatar")
    public ResponseDto<Void> changeUserAvatar(@PathVariable int id, @ModelAttribute AvatarDto requestData) throws IOException {
        userService.changeUserAvatar(id, requestData);
        return ResponseDto.success();
    }
}
