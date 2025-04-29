package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.DemoDto;
import cn.moonshotacademy.memoirs.entity.DemoEntity;

public interface DemoService {
    public DemoEntity demo(DemoDto demoDto);
}
