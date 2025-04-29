package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.DemoDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.entity.DemoEntity;
import cn.moonshotacademy.memoirs.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demos")
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @GetMapping("")
    public ResponseDto<DemoEntity> demo(@RequestBody DemoDto demoDto) {
        return ResponseDto.success(demoService.demo(demoDto));
    }
}
