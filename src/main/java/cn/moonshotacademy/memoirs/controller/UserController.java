package cn.moonshotacademy.memoirs.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.EmailService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private EmailService userService;

    @GetMapping("/reset-password")
    public ResponseDto<Void> resetPassword(@RequestParam("email") String email) {
        userService.resetPassword(email);
        return ResponseDto.success();
    }

}