package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.TagDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
    List<TagDto> getRecommendedTags();
}
