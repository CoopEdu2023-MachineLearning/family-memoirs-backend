package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.service.ImageService;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private final Path imageLocation = Paths.get("uploads/images");

    @Override
    public Resource loadImageAsResource(String filename) {
        try {
            Path file = imageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
            throw new BusinessException(ExceptionEnum.IMAGE_NOT_FOUND);
        } catch (MalformedURLException e) {
            throw new BusinessException(ExceptionEnum.IMAGE_LOAD_FAILED);
        }
    }
}
