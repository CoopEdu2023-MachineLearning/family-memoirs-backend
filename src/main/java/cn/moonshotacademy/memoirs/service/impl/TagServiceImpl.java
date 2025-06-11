package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.TagDto;
import cn.moonshotacademy.memoirs.entity.TagEntity;
import cn.moonshotacademy.memoirs.repository.TagRepository;
import cn.moonshotacademy.memoirs.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    @Autowired private TagRepository tagRepository;

    @Override
    public List<TagDto> getRecommendedTags() {
        List<TagEntity> recommendedTags = tagRepository.findByRecommendTrue();

        return recommendedTags.stream()
                .map(tag -> TagDto.builder().id(tag.getId()).name(tag.getName()).build())
                .collect(Collectors.toList());
    }
}
