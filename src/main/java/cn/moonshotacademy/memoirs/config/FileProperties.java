package cn.moonshotacademy.memoirs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileProperties {
    private String storageLocation = "files";
    private int maxUploadSize = 1024 * 1024 * 10;
}