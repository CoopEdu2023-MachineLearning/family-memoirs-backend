package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.TagDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    List<TagDto> getRecommendedTags();
}