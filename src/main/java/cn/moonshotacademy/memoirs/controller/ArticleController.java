package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ArticleListDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.service.ArticleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    @Autowired private ArticleService articleService;

    @GetMapping("/create")
    public ResponseDto<Integer> createArticle(ArticleDto articleDto) {
        Integer result = articleService.createArticle(articleDto);
        return ResponseDto.success(result);
    }

    @PostMapping("/{id}/upload")
    public ResponseDto<Void> uploadArticle(@PathVariable int id, @RequestBody ArticleDto articleDto) {
        articleService.uploadArticle(articleDto);
        return ResponseDto.success();
    }

    @GetMapping("/{id}")
    public ResponseDto<ArticleDto> getArticleDetails(@PathVariable int id) {
        return ResponseDto.success(articleService.getArticleById(id));
    }

    @GetMapping("/all")
    public ResponseDto<List<ArticleListDto>> getArticles() {
        return ResponseDto.success(articleService.getAllArticles());
    }

    @GetMapping("/page")
    public ResponseDto<?> getArticlesPage(@RequestParam int page, @RequestParam int size) {
        return ResponseDto.success(articleService.getArticles(page, size));
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
