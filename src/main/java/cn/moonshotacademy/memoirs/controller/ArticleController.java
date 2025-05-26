package cn.moonshotacademy.memoirs.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.ArticleService;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/upload")
    public ResponseDto<Void> uploadArticle(ArticleDto articleDto) {
        articleService.uploadArticle(articleDto);
        return ResponseDto.success();
    }

    @GetMapping("/verified_list")
    public ResponseDto<List<Integer>> searchVerifiedArticle(ArticleDto articleDto) {
        List<Integer> result = articleService.searchVerifiedArticle(articleDto);
        return ResponseDto.success(result);
    }

    @GetMapping("/unverified_list")
    public ResponseDto<List<Integer>> searchUnverifiedArticle(ArticleDto articleDto) {
        List<Integer> result = articleService.searchUnverifiedArticle(articleDto);
        return ResponseDto.success(result);
    }
}