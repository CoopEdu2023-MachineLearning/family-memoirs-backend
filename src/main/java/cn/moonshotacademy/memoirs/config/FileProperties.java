package cn.moonshotacademy.memoirs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload")
@Getter
@Setter
public class FileProperties {
    private String storageLocation;
    private String userAvatarLocation;
    private String artifactAvatarLocation;

    private String storageUrlBase;
    private String userAvatarUrlBase;
    private String artifactAvatarUrlBase;
}
