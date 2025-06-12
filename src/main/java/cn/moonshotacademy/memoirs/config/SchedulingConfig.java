package cn.moonshotacademy.memoirs.config;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.entity.UserEntity;
import cn.moonshotacademy.memoirs.service.ArticleService;
import cn.moonshotacademy.memoirs.service.SearchService;
import cn.moonshotacademy.memoirs.service.TellerService;
import cn.moonshotacademy.memoirs.service.UserService;

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

    @Autowired private TellerService tellerService;

    @Autowired private UserService userService;

    @Scheduled(cron = "0 0 4 * * *")
    public void synchronizeData() throws Exception {
        List<ArticleEntity> storiesToSync = articleService.getAllArticleEntities();
        searchService.syncStories(storiesToSync);
        List<TellerEntity> tellersToSync = tellerService.getList();
        searchService.syncTellers(tellersToSync);
        List<UserEntity> usersToSync = userService.getAllUsers();
        searchService.syncUsers(usersToSync);
    }
}
