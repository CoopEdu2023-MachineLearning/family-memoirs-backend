package cn.moonshotacademy.memoirs.service.impl;

import org.springframework.stereotype.Service;

import cn.moonshotacademy.memoirs.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String test() {
        return "test";
    }
}
