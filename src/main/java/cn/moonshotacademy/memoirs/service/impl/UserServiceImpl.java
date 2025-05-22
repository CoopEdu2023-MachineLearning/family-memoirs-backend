package cn.moonshotacademy.memoirs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moonshotacademy.memoirs.dto.UserDto;
import cn.moonshotacademy.memoirs.dto.LoginDto;
import cn.moonshotacademy.memoirs.dto.SignUpDto;
import cn.moonshotacademy.memoirs.entity.UserEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import cn.moonshotacademy.memoirs.service.JwtService;
import cn.moonshotacademy.memoirs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    @Override
    public String signup(SignUpDto signUpDto) {
        // Validate required fields
        if(signUpDto.getEmail() == null ||
                signUpDto.getPassword() == null ||
                signUpDto.getUsername() == null) {
            throw new BusinessException(ExceptionEnum.MISSING_PARAMETERS);
        }

        // Check if username already exists
        if(userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new BusinessException(ExceptionEnum.USERNAME_ALREADY_EXISTS);
        }

        UserEntity user = new UserEntity();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(signUpDto.getPassword());
        user.setEmail(signUpDto.getEmail());

        UserEntity savedUser = userRepository.save(user);

        // Generate JWT token
        return jwtService.setToken(savedUser.getId().intValue());
    }
    
    @Override
    public void resetPassword(UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));

        userEntity.setPassword(password);
        userRepository.save(userEntity);
    }

    @Override
    public String login(LoginDto loginDto) {
        // Validate required fields
        if(loginDto.getUsername() == null || loginDto.getPassword() == null) {
            throw new BusinessException(ExceptionEnum.MISSING_PARAMETERS);
        }

        // Find user by username
        Optional<UserEntity> userOptional = userRepository.findByUsername(loginDto.getUsername());

        // Check if user exists and password matches
        if(userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if(user.getPassword().equals(loginDto.getPassword())) {
                return jwtService.setToken(user.getId().intValue());
            }
        }

        throw new BusinessException(ExceptionEnum.INVALID_CREDENTIALS);
    }
}