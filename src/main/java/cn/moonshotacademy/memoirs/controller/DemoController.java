package cn.moonshotacademy.memoirs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.DemoService;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @GetMapping("")
    public ResponseDto<String> demo() {
        return ResponseDto.success(demoService.test());
    }
}
