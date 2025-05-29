package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.WaterDto;
import org.springframework.data.domain.Page;

public interface ArticleService {
    Page<WaterDto> getArticles(int page, int size);
}
