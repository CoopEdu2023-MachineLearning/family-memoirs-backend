package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.service.TellerService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TellerServiceImpl implements TellerService {
    private final Path imageLocation = Paths.get("uploads/avatars/teller");

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
    }
