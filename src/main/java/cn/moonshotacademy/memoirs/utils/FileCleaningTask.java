package cn.moonshotacademy.memoirs.utils;

import cn.moonshotacademy.memoirs.entity.FileEntity;
import cn.moonshotacademy.memoirs.repository.FileRepository;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileCleaningTask {

    @Autowired private FileRepository fileRepository;

    // 每1分钟执行一次
    @Scheduled(cron = "0 1 1 * * ?")
    public void cleanDeletedFiles() {
        System.out.println("文件清理任务开始...");

        // 查询所有被标记为 is_deleted = true 的文件
        List<FileEntity> deletedFiles = fileRepository.findAllByIsDeletedTrue();

        for (FileEntity fileEntity : deletedFiles) {
            String filePath = fileEntity.getUrl(); // 假设你的 FileEntity 有 getPath() 方法

            File file = new File(filePath);
            if (file.exists()) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("成功删除本地文件: " + filePath);
                } else {
                    System.out.println("删除失败: " + filePath);
                }
            } else {
                System.out.println("文件不存在，可能已被删除: " + filePath);
            }
        }
    }
}
