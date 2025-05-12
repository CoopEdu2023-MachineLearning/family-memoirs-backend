package cn.moonshotacademy.memoirs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.moonshotacademy.memoirs.dto.EmailDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.EmailService;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/getCode")
    public ResponseDto<Void> getCode(@RequestBody EmailDto emailDto) {
        emailService.getCode(emailDto);
        return ResponseDto.success();
    }

    @PostMapping("/verifyCode")
    public ResponseDto<Void> verifyCode(@RequestBody EmailDto emailDto) {
        emailService.verifyCode(emailDto);
        return ResponseDto.success();
    }

}