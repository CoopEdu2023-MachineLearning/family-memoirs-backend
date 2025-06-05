package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.TagDto;
import cn.moonshotacademy.memoirs.dto.WaterDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.TagEntity;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.repository.ArticleRepository;
import cn.moonshotacademy.memoirs.repository.TellerRepository;
import cn.moonshotacademy.memoirs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TellerRepository tellerRepository;


    @Override
    public Page<WaterDto> getArticles(int page, int size) {
        return articleRepository.findAll(PageRequest.of(page, size))
                .map(this::convertToDto);
    }

    private WaterDto convertToDto(ArticleEntity entity) {
        String tellerName = "";
        TellerEntity teller = null;
        if (entity.getTellerId() != null) {
            Optional<TellerEntity> tellerO = tellerRepository.findById(entity.getTellerId());
            if(tellerO.isPresent()) {
                teller = tellerO.get();
            }
        }

        return WaterDto.builder()
                .id(entity.getId())
                .location(entity.getLocation())
                .text(entity.getText())
                .teller(teller)
                .user("User#" + entity.getUserId())
                .description(entity.getDescription())
                .images(Collections.emptyList())
                .audio(Collections.emptyList())
                .tags(entity.getTagList().stream().map(this::toTagDto).collect(Collectors.toList()))
                .textStatus(entity.getTextStatus())
                .descriptionStatus(entity.getDescriptionStatus())
                .era(entity.getEra())
                .startYear(entity.getStartYear())
                .endYear(entity.getEndYear())
                .startMonth(entity.getStartMonth())
                .endMonth(entity.getEndMonth())
                .build();
    }

    private TagDto toTagDto(TagEntity tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
