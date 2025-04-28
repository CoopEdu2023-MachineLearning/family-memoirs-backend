package cn.moonshotacademy.memoirs.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleFilesDto {
    private List<MultipartFile> files;
}
