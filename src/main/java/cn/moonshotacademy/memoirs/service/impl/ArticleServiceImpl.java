package cn.moonshotacademy.memoirs.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ImageDto;
import cn.moonshotacademy.memoirs.dto.TagDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.ImageEntity;
import cn.moonshotacademy.memoirs.entity.TagEntity;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.ArticleRepository;
import cn.moonshotacademy.memoirs.repository.ImageRepository;
import cn.moonshotacademy.memoirs.repository.TellerRepository;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import cn.moonshotacademy.memoirs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final TellerRepository tellerRepository;

    @Override
    public void uploadArticle(ArticleDto articleDto) {
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(articleDto, articleEntity); // copyProperties(source, target)
        articleRepository.save(articleEntity);
    }
    public ArticleDto getArticleById(int id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.ARTICLE_NOT_FOUND));

        String username = userRepository.findById(article.getUserId())
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND))
                .getUsername();

        TellerEntity teller = tellerRepository.findById(article.getTellerId())
                .orElseThrow(() -> new BusinessException(ExceptionEnum.TELLER_NOT_FOUND));

        TellerDto tellerDto = TellerDto.builder()
                .id(teller.getId())
                .name(teller.getName())
                .intro(teller.getIntro())
                .avatar_url(teller.getAvatar_url())
                .gender(teller.getGender())
                .birthplace(teller.getBirthplace())
                .birthdate(teller.getBirthdate())
                .build();

        List<ImageEntity> images = imageRepository.findAllByArticleId((long) id);

        List<ImageDto> imageDto = images.stream()
                .map(image -> ImageDto.builder()
                        .id(Math.toIntExact(image.getId()))
                        .imageUrl(image.getImageUrl())
                        .build())
                .toList();

        List<TagEntity> tags = articleRepository.findTagsByArticleId(id);

        List<TagDto> tagDto = tags.stream()
                .map(tag -> TagDto.builder()
                        .id(tag.getId())  // 直接使用 Integer 类型
                        .name(tag.getName())
                        .build())
                .toList();

        return ArticleDto.builder()
                .id(article.getId())
                .era(article.getEra())
                .teller(tellerDto)
                .startYear(article.getStartYear())
                .endYear(article.getEndYear())
                .startMonth(article.getStartMonth())
                .endMonth(article.getEndMonth())
                .location(article.getLocation())
                .text(article.getText())
                .user(username)
                .images(imageDto)
                .tags(tagDto)
                .description(article.getDescription())
                .textStatus(article.getTextStatus())
                .descriptionStatus(article.getDescriptionStatus())
                .build();
    }
}