package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.dto.DeleteFileDto;
import cn.moonshotacademy.memoirs.dto.MultipleFilesDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.service.FileService;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/batch")
    public ResponseDto<List<Integer>> uploadMultipleFiles(@ModelAttribute MultipleFilesDto files)
            throws IOException {
        if (files.getFiles().size() == 1 && files.getFiles() == null)
            throw new BusinessException(ExceptionEnum.NULL_FILELIST);
        for (MultipartFile file : files.getFiles()) {
            if (file.getOriginalFilename().isBlank()) {
                throw new BusinessException(ExceptionEnum.NULL_FILENAME);
            }
        }
        List<Integer> result = fileService.uploadMultipleFiles(files);
        return ResponseDto.success(result);
    }

    @PostMapping("delete")
    public ResponseDto<Void> deleteFiles(@RequestBody DeleteFileDto deleteFileDto) {
        fileService.deleteFile(deleteFileDto.getFileId());
        return ResponseDto.success();
    }
}
