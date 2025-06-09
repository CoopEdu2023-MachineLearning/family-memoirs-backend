package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.*;
import cn.moonshotacademy.memoirs.entity.*;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.*;
import cn.moonshotacademy.memoirs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final AudioRepository audioRepository;
    private final TellerRepository tellerRepository;

    @Override
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
                .nameOld(teller.getNameOld())
                .introOld(teller.getIntroOld())
                .avatarUrlOld(teller.getAvatarUrlOld())
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

        List<AudioEntity> audios = audioRepository.findAllByArticleId((long) id);

        List<AudioDto> audioDto = audios.stream()
                .map(audio -> AudioDto.builder()
                        .id(Math.toIntExact(audio.getId()))
                        .audioUrl(audio.getAudioUrl())
                        .name(audio.getName())
                        .duration(audio.getDuration())
                        .status(audio.getStatus())
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
                .audio(audioDto)
                .tags(tagDto)
                .description(article.getDescription())
                .textStatus(article.getTextStatus())
                .descriptionStatus(article.getDescriptionStatus())
                .build();
    }
}