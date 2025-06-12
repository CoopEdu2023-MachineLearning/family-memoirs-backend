package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.UploadDto;
import cn.moonshotacademy.memoirs.entity.FileEntity;

public interface FileService {

    public FileEntity upload(UploadDto uploadDto);

}
