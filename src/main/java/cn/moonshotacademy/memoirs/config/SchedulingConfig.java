package cn.moonshotacademy.memoirs.config;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.service.ArticleService;
import cn.moonshotacademy.memoirs.service.SearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired private SearchService searchService;

    @Autowired private ArticleService articleService;

    @Scheduled(cron = "0 0 4 * * *")
    public void synchronizeData() throws Exception {
        List<ArticleEntity> storiesToSync = articleService.getAllArticleEntities();
        searchService.syncStories(storiesToSync);
    }
}
