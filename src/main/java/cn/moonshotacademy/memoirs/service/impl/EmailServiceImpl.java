package cn.moonshotacademy.memoirs.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.moonshotacademy.memoirs.dto.EmailDto;
import cn.moonshotacademy.memoirs.entity.UserEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import cn.moonshotacademy.memoirs.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String GET_CODE_URL = "http://127.0.0.1:8000/send-verification-code";

    private static final String VERIFY_CODE_URL = "http://127.0.0.1:8000/verify-code";

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserRepository userRepository;

    @Override
    public void getCode(EmailDto emailDto) {

        String email = emailDto.getEmail();

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.EMAIL_NOT_FOUND));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("to_address", email);
        requestBody.put("subject", "【家书万金】邮箱验证码");
        requestBody.put("from_alias", "家书万金");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            restTemplate.postForObject(GET_CODE_URL, request, String.class);
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.EMAIL_REQEUST_FAILED);
        }
    }

    @Override
    public void verifyCode(EmailDto emailDto) {

        String email = emailDto.getEmail();
        String code = emailDto.getCode();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("address", email);
        requestBody.put("code", code);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            restTemplate.postForObject(VERIFY_CODE_URL, request, String.class);
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.EMAIL_REQEUST_FAILED);
        }
    }
}
