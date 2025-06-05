package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.UserDto;
import cn.moonshotacademy.memoirs.entity.UserEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import cn.moonshotacademy.memoirs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUserInfo(int id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));
        return(
                UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .build());
    }

    @Override
    public void changeUserName(int id, UserDto username) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));
        if (username == null || username.getUsername() == null || username.getUsername().isEmpty()) {
            throw new BusinessException(ExceptionEnum.MISSING_PARAMETERS);
        }
        if (userRepository.existsByUsername(username.getUsername())) {
            throw new BusinessException(ExceptionEnum.USERNAME_ALREADY_EXISTS);
        }
        user.setUsernameNew(username.getUsername());
        user.setUsernameStatus("审核中");
        userRepository.save(user);
    }

    @Override
    public void changeUserEmail(int id, String newEmail) {
        // Implementation for changing user email
    }

    @Override
    public void changeUserPassword(int id, String oldPassword, String newPassword) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BusinessException(ExceptionEnum.INVALID_PASSWORD);
        }

        if (newPassword == null || newPassword.isEmpty()) {
            throw new BusinessException(ExceptionEnum.MISSING_PARAMETERS);
        }

        /* 可以添加更多密码强度验证规则
        if (newPassword.length() < 8) {
            throw new BusinessException(ExceptionEnum.PASSWORD_TOO_WEAK);
        }
         */

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void changeUserAvatar(int id, String avatarUrl) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));
        

    }
}
