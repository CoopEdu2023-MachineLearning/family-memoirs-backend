package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.ArticleService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/upload")
    public ResponseDto<Void> uploadArticle(@RequestBody ArticleDto articleDto) {
        articleService.uploadArticle(articleDto);
        return ResponseDto.success();
    }

    @GetMapping("/{id}")
    public ResponseDto<ArticleDto> getArticleDetails(@PathVariable int id) {
        return ResponseDto.success(articleService.getArticleById(id));
    }
    
    @GetMapping("/all")
    public ResponseDto<List<ArticleDto>> getArticles() {
        return ResponseDto.success(articleService.getAllArticles());
    }
}