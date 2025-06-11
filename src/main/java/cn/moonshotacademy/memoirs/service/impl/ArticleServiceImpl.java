package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ArticleListDto;
import cn.moonshotacademy.memoirs.dto.AudioDto;
import cn.moonshotacademy.memoirs.dto.ImageDto;
import cn.moonshotacademy.memoirs.dto.TagDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.dto.WaterDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.AudioEntity;
import cn.moonshotacademy.memoirs.entity.ImageEntity;
import cn.moonshotacademy.memoirs.entity.TagEntity;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.ArticleRepository;
import cn.moonshotacademy.memoirs.repository.AudioRepository;
import cn.moonshotacademy.memoirs.repository.ImageRepository;
import cn.moonshotacademy.memoirs.repository.TellerRepository;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import cn.moonshotacademy.memoirs.service.ArticleService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final AudioRepository audioRepository;
    private final TellerRepository tellerRepository;

    @Override
    public void uploadArticle(ArticleDto articleDto) {
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(articleDto, articleEntity); // copyProperties(source, target)
        articleRepository.save(articleEntity);
    }

    @Override
    public ArticleDto getArticleById(int id) {
        ArticleEntity article =
                articleRepository
                        .findById(id)
                        .orElseThrow(() -> new BusinessException(ExceptionEnum.ARTICLE_NOT_FOUND));

        String username =
                userRepository
                        .findById(article.getUserId())
                        .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND))
                        .getUsername();

        TellerEntity teller =
                tellerRepository
                        .findById(article.getTellerId())
                        .orElseThrow(() -> new BusinessException(ExceptionEnum.TELLER_NOT_FOUND));

        TellerDto tellerDto =
                TellerDto.builder()
                        .id(teller.getId())
                        .nameOld(teller.getNameOld())
                        .introOld(teller.getIntroOld())
                        .avatarUrlOld(teller.getAvatarUrlOld())
                        .gender(teller.getGender())
                        .birthplace(teller.getBirthplace())
                        .birthdate(teller.getBirthdate())
                        .build();

        List<ImageEntity> images = imageRepository.findAllByArticleId((long) article.getId());

        List<ImageDto> imageDto =
                images.stream()
                        .map(
                                image ->
                                        ImageDto.builder()
                                                .id(Math.toIntExact(image.getId()))
                                                .imageUrl(image.getImageUrl())
                                                .build())
                        .toList();

        List<AudioEntity> audios = audioRepository.findAllByArticleId((long) id);

        List<AudioDto> audioDto =
                audios.stream()
                        .map(
                                audio ->
                                        AudioDto.builder()
                                                .id(Math.toIntExact(audio.getId()))
                                                .audioUrl(audio.getAudioUrl())
                                                .name(audio.getName())
                                                .duration(audio.getDuration())
                                                .status(audio.getStatus())
                                                .build())
                        .toList();

        List<TagEntity> tags = articleRepository.findTagsByArticleId(article.getId());

        List<TagDto> tagDto =
                tags.stream()
                        .map(tag -> TagDto.builder().id(tag.getId()).name(tag.getName()).build())
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
                .audio(audioDto)
                .tags(tagDto)
                .description(article.getDescription())
                .textStatus(article.getTextStatus())
                .descriptionStatus(article.getDescriptionStatus())
                .build();
    }

    @Override
    public List<ArticleListDto> getAllArticles() {
        List<ArticleEntity> articles = articleRepository.findAll();

        return articles.stream()
                .map(
                        article -> {
                            // 获取文章的标签
                            List<TagEntity> tags = articleRepository.findTagsByArticleId(article.getId());

                            List<TagDto> tagDtos =
                                    tags.stream()
                                            .map(tag -> TagDto.builder().id(tag.getId()).name(tag.getName()).build())
                                            .collect(Collectors.toList());

                            return ArticleListDto.builder()
                                    .id(article.getId())
                                    .location(article.getLocation() != null ? article.getLocation() : "")
                                    .startYear(article.getStartYear())
                                    .startMonth(article.getStartMonth())
                                    .era(String.valueOf(article.getEra()))
                                    .tags(tagDtos)
                                    .build();
                        })
                .collect(Collectors.toList());
    }

    @Override
    public Page<WaterDto> getArticles(int page, int size) {
        return articleRepository.findAll(PageRequest.of(page, size)).map(this::convertToDto);
    }

    private WaterDto convertToDto(ArticleEntity entity) {
        String tellerName = "";
        TellerEntity teller = null;
        if (entity.getTellerId() != null) {
            Optional<TellerEntity> tellerO = tellerRepository.findById(entity.getTellerId());
            if (tellerO.isPresent()) {
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
        return TagDto.builder().id(tag.getId()).name(tag.getName()).build();
    }
}
