package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TagDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.RelationEntity;
import cn.moonshotacademy.memoirs.entity.TagEntity;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.ArticleRepository;
import cn.moonshotacademy.memoirs.repository.ImageRepository;
import cn.moonshotacademy.memoirs.repository.RelationRepository;
import cn.moonshotacademy.memoirs.repository.TellerRepository;
import cn.moonshotacademy.memoirs.service.TellerService;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TellerServiceImpl implements TellerService {
    private final Path imageLocation = Paths.get("uploads/avatars/teller");
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    private final TellerRepository tellerRepository;
    private final RelationRepository relationRepository;

    @Override
    public TellerEntity getTellerById(Integer tellerId) {
        return tellerRepository
                .findById(tellerId)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.TELLER_NOT_FOUND));
    }

    @Override
    public Resource LoadAvatarAsResource(String filename) {
        try {
            Path file = imageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
            throw new BusinessException(ExceptionEnum.AVATAR_NOT_FOUND);
        } catch (MalformedURLException e) {
            throw new BusinessException(ExceptionEnum.AVATAR_LOAD_FAILED);
        }
    }

    @Override
    public List<ArticleDto> getArticlesByTellerId(Integer tellerId) {
        List<ArticleEntity> articles = articleRepository.findByTellerId(tellerId);
        return articles.stream()
                .map(
                        article -> {
                            List<TagEntity> tags = articleRepository.findTagsByArticleId(article.getId());

                            List<TagDto> tagDto =
                                    tags.stream()
                                            .map(
                                                    tag ->
                                                            TagDto.builder()
                                                                    .id(tag.getId()) // 直接使用 Integer 类型
                                                                    .name(tag.getName())
                                                                    .build())
                                            .toList();

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

                            // 构建ArticleDto对象
                            return ArticleDto.builder()
                                    .id(article.getId())
                                    .era(article.getEra())
                                    .startYear(article.getStartYear())
                                    .endYear(article.getEndYear())
                                    .startMonth(article.getStartMonth())
                                    .endMonth(article.getEndMonth())
                                    .location(article.getLocation())
                                    .text(article.getText())
                                    .description(article.getDescription())
                                    .textStatus(article.getTextStatus())
                                    .descriptionStatus(article.getDescriptionStatus())
                                    .tags(tagDto)
                                    .teller(tellerDto)
                                    .build();
                        })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto<TellerDto> createTeller(TellerDto tellerDto) {
        try {
            Integer userId = tellerDto.getUserId();
            if (userId == null) {
                throw new BusinessException(ExceptionEnum.INVALID_USER_ID);
            }

            List<TellerEntity> existingTellers = tellerRepository.findByUserId(userId);
            boolean hasSelf = existingTellers.stream().anyMatch(t -> "我自己".equals(t.getIdentity()));

            String identity;
            Integer relationId = null;
            String relationName = tellerDto.getRelationName();

            if (existingTellers.isEmpty()) {
                identity = "我自己";
            } else if (hasSelf) {
                if (relationName == null || relationName.trim().isEmpty()) {
                    throw new BusinessException(ExceptionEnum.INVALID_RELATION);
                }
                // 查询或创建 relation 记录
                Optional<RelationEntity> relationOpt = relationRepository.findByName(relationName);
                RelationEntity relation;
                if (relationOpt.isPresent()) {
                    relation = relationOpt.get();
                } else {
                    relation = new RelationEntity();
                    relation.setName(relationName);
                    relation = relationRepository.save(relation);
                }
                relationId = relation.getId();
                identity = "我自己的" + relationName;
            } else {
                throw new BusinessException(ExceptionEnum.MUST_CREATE_SELF_FIRST);
            }

            TellerEntity teller = new TellerEntity();
            teller.setIdentity(tellerDto.getIdentity());
            teller.setNameNew(tellerDto.getNameNew());
            teller.setGender(tellerDto.getGender());
            teller.setBirthplace(tellerDto.getBirthplace());
            teller.setBirthdate(tellerDto.getBirthdate());
            teller.setIntroNew(tellerDto.getIntroNew());
            teller.setAvatarUrlNew(tellerDto.getAvatarUrlNew());
            teller.setUserId(userId);
            teller.setNameState("pending");
            teller.setIntroState("pending");
            teller.setAvatarState("pending");
            TellerEntity savedTeller = tellerRepository.save(teller);
            return ResponseDto.success(tellerDto);
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.TELLER_CREATE_FAILED);
        }
    }

    @Override
    public List<TellerEntity> getTellersByUserId(Integer userId) {
        if (userId == null) {
            throw new BusinessException(ExceptionEnum.INVALID_USER_ID);
        }
        return tellerRepository.findByUserId(userId);
    }

    @Override
    public TellerEntity[] getList() {
        TellerEntity[] tellers = tellerRepository.findAll().toArray(new TellerEntity[0]);
        if (tellers == null || tellers.length == 0) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        return tellers;
    }

    @Override
    public TellerEntity findTeller(String name) {
        TellerEntity teller =
                tellerRepository
                        .findByNameOld(name)
                        .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));
        if (teller == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        return teller;
    }
}
