package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/page")
    public ResponseDto<?> getArticlesPage(@RequestParam int page, @RequestParam int size) {
        return ResponseDto.success(articleService.getArticles(page, size));
    }
}