package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.UploadDto;
import cn.moonshotacademy.memoirs.entity.FileEntity;
import cn.moonshotacademy.memoirs.service.FileService;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseDto<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ExceptionEnum.EMPTY_FILE);
        }
        UploadDto uploadDto = new UploadDto();
        uploadDto.setFile(file);
        return ResponseDto.success(fileService.upload(uploadDto));
    }
}
