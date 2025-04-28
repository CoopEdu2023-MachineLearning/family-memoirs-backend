
package cn.moonshotacademy.memoirs.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;

public class FileNamingUtils {

    // Ensure the directory exists, if not, create it
    public static void ensureDirectoryExists(File directory) {
        if (!directory.exists() && !directory.mkdirs()) {
            throw new BusinessException(ExceptionEnum.FAIL_UPLOAD);
        }
    }

    // Generate a new file name with timestamp to avoid conflicts
    public static String getNewFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            throw new BusinessException(ExceptionEnum.NULL_FILENAME);
        }

        String extension = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);  // Get the file extension
        }

        String timestamp = String.valueOf(System.currentTimeMillis());
        String baseName = originalFilename.substring(0, dotIndex);
        return baseName + "_" + timestamp + extension;  // Construct the new file name
    }
}