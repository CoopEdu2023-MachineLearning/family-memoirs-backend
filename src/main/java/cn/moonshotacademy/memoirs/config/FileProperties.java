package cn.moonshotacademy.memoirs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileProperties {
    private String storageLocation = "files";
    private int maxUploadSize = 1024 * 1024 * 10;
    private String userAvatarLocation;
    private String storageUrlBase;
    private String userAvatarUrlBase;
}
