package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.SignUpDto;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.moonshotacademy.memoirs.service.EmailService;
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

}
