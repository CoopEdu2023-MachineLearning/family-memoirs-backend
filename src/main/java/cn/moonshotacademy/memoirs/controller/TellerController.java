package cn.moonshotacademy.memoirs.controller;


import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.service.TellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teller")
public class TellerController {

    @Autowired
    private TellerService tellerService;

    @GetMapping("/{tellerId}")
    public ResponseDto<TellerEntity> getTellerById(@PathVariable Integer tellerId) {
        return ResponseDto.success(tellerService.getTellerById(tellerId));
    }

    @GetMapping("/avatar/{filename}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename) {
        Resource file = tellerService.LoadAvatarAsResource(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @GetMapping("/{tellerId}/articles")
    public ResponseDto<?> getArticlesByTellerId(@PathVariable Integer tellerId) {
        return ResponseDto.success(tellerService.getArticlesByTellerId(tellerId));
    }
}
