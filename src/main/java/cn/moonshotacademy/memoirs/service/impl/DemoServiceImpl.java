package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String test() {
        return "test";
    }
}
