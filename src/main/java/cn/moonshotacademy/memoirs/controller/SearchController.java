package cn.moonshotacademy.memoirs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.typesense.model.SearchResult;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.SearchResultDto;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.service.SearchService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("")
    public ResponseDto<SearchResultDto> searchAll(
            @RequestParam String q,
            @RequestParam Optional<Integer> snippetLength,
            @RequestParam Optional<Boolean> preciseSearch) {
        if (q == null) {
            throw new BusinessException(ExceptionEnum.MISSING_PARAMETERS);
        }
        SearchResult storiesResult = searchService.searchStories(q, snippetLength, preciseSearch);
        SearchResult tellersResult = searchService.searchTeller(q);
        SearchResult userResult = searchService.searchUser(q);
        return ResponseDto.success(new SearchResultDto(storiesResult, tellersResult, userResult));
    }

}
