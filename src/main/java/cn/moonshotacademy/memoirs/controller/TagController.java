package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TagDto;
import cn.moonshotacademy.memoirs.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    @Autowired private TagService tagService;

    @GetMapping("/recommended")
    public ResponseDto<List<TagDto>> getRecommendedTags() {
        return ResponseDto.success(tagService.getRecommendedTags());
    }
}
