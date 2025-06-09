package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.config.FileProperties;
import cn.moonshotacademy.memoirs.dto.MultipleFilesDto;
import cn.moonshotacademy.memoirs.entity.FileEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.FileRepository;
import cn.moonshotacademy.memoirs.repository.FileVersionRepository;
import cn.moonshotacademy.memoirs.service.FileService;
import cn.moonshotacademy.memoirs.utils.FileNamingUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private final FileProperties fileProperties;
    private final FileRepository fileRepository;
    private final FileVersionRepository fileVersionRepository;

    @Autowired
    public FileServiceImpl(
            FileProperties fileProperties,
            FileRepository fileRepository,
            FileVersionRepository fileVersionRepository) {
        this.fileProperties = fileProperties;
        this.fileRepository = fileRepository;
        this.fileVersionRepository = fileVersionRepository;
    }

    @Override
    @Transactional
    public List<Integer> uploadMultipleFiles(MultipleFilesDto RequestDto) throws IOException {
        List<Integer> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : RequestDto.getFiles()) {
            String originalFilename = FileNamingUtils.getNewFileName(file);
            String baseName = originalFilename;
            if (originalFilename.isBlank()) {
                throw new BusinessException(ExceptionEnum.NULL_FILENAME);
            }
            String filePath = fileProperties.getStorageLocation() + File.separator + originalFilename;
            File destination = new File(filePath);
            FileNamingUtils.ensureDirectoryExists(destination.getParentFile());
            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());
            FileEntity uploadedFile = new FileEntity();
            uploadedFile.setName(baseName);
            uploadedFile.setType(Files.probeContentType(path));
            uploadedFile.setUrl(filePath);
            fileRepository.save(uploadedFile);
            uploadedFiles.add(uploadedFile.getId());
        }
        if (uploadedFiles.size() != RequestDto.getFiles().size()) {
            throw new BusinessException(ExceptionEnum.FAIL_UPLOAD);
        }

        return uploadedFiles;
    }

    @Override
    public void deleteFile(Long id) {
        Optional<FileEntity> file = fileRepository.findById(id);
        if (!file.isPresent()) throw new BusinessException(ExceptionEnum.FILE_NOT_FOUND);
        else {
            FileEntity currentFile = file.get();
            currentFile.setDeleted(true);
            fileRepository.save(currentFile);
        }
    }
}
