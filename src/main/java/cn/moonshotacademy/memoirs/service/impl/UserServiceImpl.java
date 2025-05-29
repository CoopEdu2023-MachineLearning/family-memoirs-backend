package cn.moonshotacademy.memoirs.service.impl;
import cn.moonshotacademy.memoirs.dto.EmailDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.SignUpDto;
import cn.moonshotacademy.memoirs.entity.UserEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import cn.moonshotacademy.memoirs.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements cn.moonshotacademy.memoirs.service.UserService {
    private UserRepository userRepository;
    private EmailService emailService;

    @Override
    public ResponseDto<Void> signup(SignUpDto signUpDto) {
        String code = "12345678";
        UserEntity user = new UserEntity();
        if(signUpDto.getEmail() == null ||
                signUpDto.getPassword() == null ||
                signUpDto.getVerificationCode() == null ||
                signUpDto.getInvitationCode() == null){
            throw new BusinessException(ExceptionEnum.MISSING_PARAMETERS);
        }
        if(!(signUpDto.getVerificationCode().equals(code))) {
            throw new BusinessException(ExceptionEnum.INVALID_INVITATION_CODE);
        }
        EmailDto emailDto = new EmailDto();
        // Verify
        emailDto.setCode(signUpDto.getVerificationCode());
        emailDto.setEmail(signUpDto.getEmail());
        try {
            emailService.verifyCode(emailDto);
        } catch (BusinessException e) {
            throw new BusinessException(ExceptionEnum.EMAIL_REQEUST_FAILED);
        }
        //Signup success
        user.setPassword(signUpDto.getPassword());
        user.setEmail(signUpDto.getEmail());
        userRepository.save(user);
        return ResponseDto.success();
    }
}