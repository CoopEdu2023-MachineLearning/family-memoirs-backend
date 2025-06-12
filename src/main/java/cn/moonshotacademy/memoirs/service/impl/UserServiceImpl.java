package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.config.FileProperties;
import cn.moonshotacademy.memoirs.dto.AvatarDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.dto.EmailDto;

import org.springframework.stereotype.Service;

import cn.moonshotacademy.memoirs.dto.UserDto;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.dto.LoginDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.SignUpDto;
import cn.moonshotacademy.memoirs.entity.UserEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.TellerRepository;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import cn.moonshotacademy.memoirs.service.EmailService;
import cn.moonshotacademy.memoirs.service.JwtService;
import cn.moonshotacademy.memoirs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FileProperties fileProperties;
    private final TellerRepository tellerRepository;
    private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("jpg", "jpeg", "png");
    private final JwtService jwtService;
    private final EmailService emailService;

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
            throw new BusinessException(ExceptionEnum.EMAIL_REQUEST_FAILED);
        }
        //Signup success
        user.setPassword(signUpDto.getPassword());
        user.setEmail(signUpDto.getEmail());
        userRepository.save(user);
        return ResponseDto.success();
    }

    @Override
    public String login(LoginDto loginDto) {
        // Validate required fields
        if(loginDto.getEmail() == null || loginDto.getPassword() == null) {
            throw new BusinessException(ExceptionEnum.MISSING_PARAMETERS);
        }

        // Find user by email
        Optional<UserEntity> userOptional = userRepository.findByEmail(loginDto.getEmail());

        // Check if user exists and password matches
        if(userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if(user.getPassword().equals(loginDto.getPassword())) {
                return jwtService.setToken(user.getId());
            }
        }

        throw new BusinessException(ExceptionEnum.INVALID_CREDENTIALS);
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
    public UserDto getUserInfo(int id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));
        List<TellerEntity> teller = tellerRepository.findAllByUserId(user.getId());

        List<TellerDto> tellerDto = teller.stream()
                .map(tellers -> TellerDto.builder()
                        .id(tellers.getId())
                        .nameOld(tellers.getNameOld())
                        .introOld(tellers.getIntroOld())
                        .avatarUrlOld(tellers.getAvatarUrlOld())
                        .gender(tellers.getGender())
                        .birthplace(tellers.getBirthplace())
                        .birthdate(tellers.getBirthdate())
                        .build())
                .toList();

        return(
                UserDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .avatarUrl(user.getAvatarUrl())
                        .teller(tellerDto)
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
    public void changeUserAvatar(int id, AvatarDto requestData) throws IOException {
        MultipartFile image = requestData.getImage();
        String originalFilename = getNewFileName(image);
        validateFileType(image);

        if (originalFilename.isBlank()) {
            throw new BusinessException(ExceptionEnum.NULL_FILENAME);
        }

        UserEntity targetEntity = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));

        // 使用配置的文件路径
        String avatarBaseDir = fileProperties.getUserAvatarLocation();
        // 确保目录存在
        Path storageDirectory = Paths.get(avatarBaseDir);
        ensureDirectoryExists(storageDirectory.toFile());

        // 完整的文件存储路径
        Path destinationPath = storageDirectory.resolve(originalFilename);
        Files.write(destinationPath, image.getBytes());

        // 创建缩略图
        createThumbnailedImage(destinationPath.toString(), 200, 200, true);

        // 修正URL路径格式 - 使用访问URL而非文件系统路径
        String avatarUrl = fileProperties.getUserAvatarUrlBase() + "/" + originalFilename;
        targetEntity.setAvatarUrl(avatarUrl);
        userRepository.save(targetEntity);
    }

    private static String getNewFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            throw new BusinessException(ExceptionEnum.NULL_FILENAME);
        }

        String extension = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
        }

        String timestamp = String.valueOf(System.currentTimeMillis());
        String baseName = originalFilename.substring(0, dotIndex);
        return baseName + "_" + timestamp + extension;
    }

    private void validateFileType(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            throw new BusinessException(ExceptionEnum.NULL_FILENAME);
        }

        String extension = getFileExtension(originalFilename).toLowerCase();
        if (!ALLOWED_FILE_TYPES.contains(extension)) {
            throw new BusinessException(ExceptionEnum.TYPE_NOTALLOW);
        }
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        return (dotIndex > 0) ? filename.substring(dotIndex + 1) : "";
    }

    private void ensureDirectoryExists(File directory) {
        if (!directory.exists() && !directory.mkdirs()) {
            throw new BusinessException(ExceptionEnum.FILE_UPLOAD_ERROR);
        }
    }

    public String createThumbnailedImage(String inputFilePath, int width, int height, Boolean isunique) {
        String outputFilePath;
        if (isunique) {
            outputFilePath = inputFilePath;
        } else {
            outputFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf(".")) + "_thumb" + inputFilePath.substring(inputFilePath.lastIndexOf("."));
        }
        try {
            Thumbnails.of(inputFilePath)
                    .size(width, height)  // 设置缩略图的大小
                    .toFile(outputFilePath);  // 指定输出文件
            return outputFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ExceptionEnum.FILE_UPLOAD_ERROR);
        }
    }
}
