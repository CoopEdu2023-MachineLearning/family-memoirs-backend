package cn.moonshotacademy.memoirs.controller;

import cn.moonshotacademy.memoirs.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    private AudioService audioService;

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getAudio(@PathVariable String filename) {
        try {
            Resource file = audioService.loadAudioAsResource(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("audio/mpeg")) // 或其他适当的音频MIME类型
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

