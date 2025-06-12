package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.MultipleFilesDto;
import cn.moonshotacademy.memoirs.dto.UploadDto;
import java.io.IOException;
import java.util.List;

public interface FileService {

    public List<Integer> uploadMultipleFiles(MultipleFilesDto files) throws IOException;

    public void deleteFile(Long id);

    public int upload(UploadDto uploadDto);
}
