package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.service.FileUploadService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final String UPLOAD_DIR = "src/main/resources/static/files";

    @Override
    public ResponseDto<String> uploadFile(MultipartFile file) {
        try {
            // 生成唯一的文件名
            String fileName =
                    UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath); // 确保目录存在

            // 存储文件
            Path targetLocation = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 生成文件访问 URL
            String fileUrl = "http://localhost:8080/files/" + fileName;

            // 返回成功响应
            return ResponseDto.success(fileUrl);
        } catch (IOException e) {
            // 返回错误响应
            throw new BusinessException(ExceptionEnum.FILE_UPLOAD_FILE);
        }
    }
}
