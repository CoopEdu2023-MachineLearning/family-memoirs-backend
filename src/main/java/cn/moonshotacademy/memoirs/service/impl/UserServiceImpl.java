package cn.moonshotacademy.memoirs.service.impl;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.SignUpDto;
import cn.moonshotacademy.memoirs.entity.UserEntity;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements cn.moonshotacademy.memoirs.service.UserService {
    private UserRepository userRepository;
    @Override
    public String signup(SignUpDto signUpDto) {
        UserEntity user = new UserEntity();

        if(signUpDto.getEmail() != null ||
                signUpDto.getPassword() != null ||
                signUpDto.getUsername() != null ||
                signUpDto.getVerificationCode() != null ||
                signUpDto.getInvitationCode() != null){
            user.setUsername(signUpDto.getUsername());
            user.setPassword(signUpDto.getPassword());
            user.setEmail(signUpDto.getEmail());
            userRepository.save(user);
            return ResponseDto.success().getMessage();
        }
        return ResponseDto.error(ExceptionEnum.MISSING_PARAMETERS.getCode(), ExceptionEnum.MISSING_PARAMETERS.getMessage() ).getMessage();
    }
}