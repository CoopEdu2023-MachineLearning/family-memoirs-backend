package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {
    private final Path imageLocation = Paths.get("uploads/images");

    @Override
    public Resource loadImageAsResource(String filename) {
        try {
            Path file = imageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
            throw new RuntimeException("Could not find file: " + filename);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not load file: " + filename, e);
        }
    }
}