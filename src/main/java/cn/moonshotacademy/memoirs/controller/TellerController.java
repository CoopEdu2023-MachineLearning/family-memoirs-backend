package cn.moonshotacademy.memoirs.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.service.TellerService;

@RestController
@RequestMapping("/teller")
@RequiredArgsConstructor
public class TellerController {

    @Autowired
    private TellerService tellerService;

    @PostMapping("/list")
    public ResponseDto<TellerEntity[]> getList() {
        TellerEntity[] teller = tellerService.getList();
        return ResponseDto.success(teller);
    }

    @PostMapping("/find")
    public ResponseDto<TellerEntity> findLearner(@RequestParam String name) {
        TellerEntity teller = tellerService.findTeller(name);
        return ResponseDto.success(teller);
    }

}