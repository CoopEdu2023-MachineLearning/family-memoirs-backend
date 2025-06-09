package cn.moonshotacademy.memoirs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    MISSING_PARAMETERS(1001, "Missing parameters"),
    ILLEGAL_PARAMETERS(1002, "Illegal parameters"),

    USER_NOT_FOUND(2001, "User not found"),
    USER_EXISTS(2002, "User exists"),
    WRONG_PASSWORD(2003, "Wrong password"),
    USERNAME_ALREADY_EXISTS(2004, "Username already exists"),
    EMAIL_ALREADY_EXISTS(2005, "Email already exists"),
    INVALID_PASSWORD(2006, "Invalid password"),
    PASSWORD_TOO_WEAK(2007, "Password too weak"),
    EMAIL_NOT_FOUND(2008, "Email not found"),
    EMAIL_REQUEST_FAILED(2009, "Email request failed"),
    INVALID_CREDENTIALS(2010, "Invalid credentials"),

    ARTICLE_NOT_FOUND(3001, "Article not found"),

    IMAGE_NOT_FOUND(4001, "Image not found"),
    IMAGE_LOAD_FAILED(4002, "Image load failed"),

    AUDIO_NOT_FOUND(5001, "Audio not found"),
    AUDIO_LOAD_FAILED(5002, "Audio load failed"),

    TELLER_NOT_FOUND(6001, "Teller not found"),

    TAG_NOT_FOUND(7001, "Tag not found"),

    AVATAR_NOT_FOUND(8001, "Avatar not found"),
    AVATAR_LOAD_FAILED(8002, "Avatar load failed"),

    NULL_FILENAME(9001, "File name empty"),
    TYPE_NOTALLOW(9002, "File type not supported"),
    FILE_UPLOAD_ERROR(9003, "File upload failed"),
    NULL_FILELIST(9004, "Cannot upload NULL filelist"),
    FILE_NOT_FOUND(9005, "File not found"),
    TELLER_CREATE_FAILED(9006, "Teller create failed"),
    FAIL_UPLOAD(9007, "Fail to write to the Database"),
    ;

    private final int code;
    private final String message;
}
