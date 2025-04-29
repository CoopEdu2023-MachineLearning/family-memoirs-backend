package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.DemoDto;
import cn.moonshotacademy.memoirs.entity.DemoEntity;
import cn.moonshotacademy.memoirs.repository.DemoRepository;
import cn.moonshotacademy.memoirs.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {

    private final DemoRepository demoRepository;

    @Override
    public DemoEntity demo(DemoDto demoDto) {
        return demoRepository.findByField(demoDto.getField());
    }
}
