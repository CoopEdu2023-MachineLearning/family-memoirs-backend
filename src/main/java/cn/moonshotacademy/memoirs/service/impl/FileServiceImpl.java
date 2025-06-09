package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.dto.UploadDto;
import cn.moonshotacademy.memoirs.entity.FileEntity;
import cn.moonshotacademy.memoirs.repository.FileRepository;
import cn.moonshotacademy.memoirs.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Value("${file.storage.disk}")
    private String disk;

    @Value("${file.storage.location}")
    private String location;

    @Override
    public int upload(UploadDto uploadDto) {

        MultipartFile file = uploadDto.getFile();

        String name = System.currentTimeMillis() + file.getOriginalFilename();
        Path root = Paths.get(disk + location).toAbsolutePath().normalize();
        Path url;
        try {
            url = root.resolve(Paths.get(name));
            Files.copy(file.getInputStream(), url);
        } catch (IOException e) {
            throw new BusinessException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }

        FileEntity fileEntity = new FileEntity(name, url.toString(), file.getContentType());
        fileRepository.save(fileEntity);

        return fileEntity.getId();
    }
}
