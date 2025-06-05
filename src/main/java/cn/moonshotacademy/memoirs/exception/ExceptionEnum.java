package cn.moonshotacademy.memoirs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    MISSING_PARAMETERS(1001, "Missing required parameters"),
    USERNAME_ALREADY_EXISTS(1002, "Username already exists"),
    INVALID_CREDENTIALS(1003, "Invalid username or password"),
    USER_EXISTS(2001, "User exists"),
    USER_NOT_FOUND(2002, "User not found"),
    ILLEGAL_PARAMETERS(1002, "Illegal parameters"),

    WRONG_PASSWORD(2003, "Wrong password"),

    ARTICLE_NOT_FOUND(3001, "Article not found"),

    IMAGE_NOT_FOUND(4001, "Image not found"),
    IMAGE_LOAD_FAILED(4002, "Image load failed"),

    AUDIO_NOT_FOUND(5001, "Audio not found"),

    TELLER_NOT_FOUND(6001, "Teller not found"),

    TAG_NOT_FOUND(7001, "Tag not found"),

    EMAIL_NOT_FOUND(3001, "Email not found"),
    EMAIL_REQEUST_FAILED(3002, "Email request failed"),
    NULL_FILENAME(4001, "File name cannot be null"),
    FAIL_UPLOAD(4003, "Fail to write to the Database"),
    NULL_FILELIST(4004, "Cannot upload NULL filelist"),
    TYPE_NOTALLOW(4005, "This file type is not allowed"),
    FILE_NOT_FOUND(3006, "File not found"),
    TELLER_CREATE_FAILED(2004, "Teller create failed"),
    FILE_UPLOAD_FILE(2005, "File upload failed"),
    ;

    private final int code;
    private final String message;
}
