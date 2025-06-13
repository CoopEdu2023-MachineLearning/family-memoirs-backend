package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import org.typesense.model.SearchResult;

public interface SearchService {
    SearchResult searchStories(
            String q, Optional<Integer> snippetLength, Optional<Boolean> preciseSearch);

    SearchResult searchTeller(String q);

    SearchResult searchUser(String q);

    void syncStories(List<ArticleEntity> stories) throws Exception;

    void syncTellers(List<TellerEntity> tellers) throws Exception;

    void syncUsers(List<UserEntity> userIds) throws Exception;

    void syncAll() throws Exception;
}
