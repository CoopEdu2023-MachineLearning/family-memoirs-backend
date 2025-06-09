package cn.moonshotacademy.memoirs.service;

import java.util.List;
import java.util.Optional;

import org.typesense.model.SearchResult;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;

public interface SearchService {
    SearchResult searchStories(String q, Optional<Integer> snippetLength, Optional<Boolean> preciseSearch);

    SearchResult searchTeller(String q);

    SearchResult searchUser(String q);

    void syncStories(List<ArticleEntity> stories) throws Exception;
}
