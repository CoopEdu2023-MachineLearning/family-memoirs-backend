package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.UserDto;
import cn.moonshotacademy.memoirs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDto<Void> changeArticleUsername(@PathVariable int id, @RequestBody UserDto username) {
        userService.changeUserName(id, username);
        return ResponseDto.success();
    }
}
