package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.service.TellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/tellers")
public class TellerController {

    @Autowired
    private TellerService tellerService;

    @PostMapping
    public ResponseDto<TellerDto> createTeller(@RequestBody TellerDto tellerDto) {
        return tellerService.createTeller(tellerDto);
    }

     @GetMapping
    public ResponseDto<List<TellerEntity>> getTellersByUserId(@RequestParam Integer userId) {
        List<TellerEntity> tellers = tellerService.getTellersByUserId(userId);
        return ResponseDto.success(tellers);
    }
}