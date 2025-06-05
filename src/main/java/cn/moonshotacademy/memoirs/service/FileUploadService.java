package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    ResponseDto<String> uploadFile(MultipartFile file);
}
