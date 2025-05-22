package cn.moonshotacademy.memoirs.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface TellerService {
    Resource LoadAvatarAsResource(String filename);
}
