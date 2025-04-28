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
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/getCode")
    public ResponseDto<Void> getCode(@RequestParam("email") String email) {
        emailService.getCode(email);
        return ResponseDto.success();
    }

    @PostMapping("/verifyCode")
    public ResponseDto<Void> verifyCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        emailService.verifyCode(email, code);
        return ResponseDto.success();
    }

}