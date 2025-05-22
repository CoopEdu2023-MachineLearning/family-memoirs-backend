package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.LoginDto;
import cn.moonshotacademy.memoirs.dto.SignUpDto;
import cn.moonshotacademy.memoirs.dto.UserDto;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseDto<Void> signup(@RequestBody SignUpDto signUpDto) {
        userService.signup(signUpDto);
        return ResponseDto.success();
    }

    @GetMapping("/reset-password")
    public ResponseDto<Void> resetPassword(UserDto userDto) {
        userService.resetPassword(userDto);
        return ResponseDto.success();
    }
    
    @PostMapping("/login")
    public ResponseDto<String> login(@RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto);
        return ResponseDto.success(token);
    }
}
