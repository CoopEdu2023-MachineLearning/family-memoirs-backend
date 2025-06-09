package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.service.AudioService;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class AudioServiceImpl implements AudioService {
    private final Path audioLocation = Paths.get("uploads/audio");

    @Override
    public Resource loadAudioAsResource(String filename) {
        try {
            Path file = audioLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
            throw new BusinessException(ExceptionEnum.AUDIO_NOT_FOUND);
        } catch (MalformedURLException e) {
            throw new BusinessException(ExceptionEnum.AUDIO_LOAD_FAILED);
        }
    }
}
