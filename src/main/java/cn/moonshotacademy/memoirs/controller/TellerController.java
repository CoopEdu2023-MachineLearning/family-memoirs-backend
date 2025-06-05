package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.service.TellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tellers")
public class TellerController {

    @Autowired
    private TellerService tellerService;

    @PostMapping
    public ResponseDto<TellerDto> createTeller(@RequestBody TellerDto tellerDto) {
        return tellerService.createTeller(tellerDto);
    }
}