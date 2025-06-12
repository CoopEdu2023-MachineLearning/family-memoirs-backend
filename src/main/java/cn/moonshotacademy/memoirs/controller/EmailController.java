package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.EmailDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    @Autowired private EmailService emailService;

    @GetMapping("/getCode")
    public ResponseDto<Void> getCode(EmailDto emailDto) {
        emailService.getCode(emailDto);
        return ResponseDto.success();
    }

    @PostMapping("/verifyCode")
    public ResponseDto<Void> verifyCode(EmailDto emailDto) {
        emailService.verifyCode(emailDto);
        return ResponseDto.success();
    }
}
