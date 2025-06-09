package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.UploadDto;
import cn.moonshotacademy.memoirs.service.FileService;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseDto<Integer> uploadFile(UploadDto uploadDto) {
        if (uploadDto.getFile().isEmpty()) {
            throw new BusinessException(ExceptionEnum.EMPTY_FILE);
        }
        return ResponseDto.success(fileService.upload(uploadDto));
    }
}
