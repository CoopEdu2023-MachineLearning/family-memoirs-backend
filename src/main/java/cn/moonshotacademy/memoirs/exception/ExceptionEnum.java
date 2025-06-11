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
    EMAIL_REQEUST_FAILED(3002, "Email request failed"),
    NULL_FILENAME(4001, "File name cannot be null"),
    FAIL_UPLOAD(4003, "Fail to write to the Database"),
    NULL_FILELIST(4004, "Cannot upload NULL filelist"),
    TYPE_NOTALLOW(4005, "This file type is not allowed"),
    FILE_NOT_FOUND(3006, "File not found"),
    TELLER_CREATE_FAILED(2004, "Teller create failed"),
    FILE_UPLOAD_FILE(2005, "File upload failed"),
    INVALID_USER_ID(2006, "无效的用户ID"),
    INVALID_RELATION(2007, "无效的亲属关系"),
    MUST_CREATE_SELF_FIRST(2008, "必须先创建‘我自己’的讲述者"),
    AVATAR_NOT_FOUND(8001, "Avatar not found"),
    AVATAR_LOAD_FAILED(8002, "Avatar load failed"),
    FILE_UPLOAD_ERROR(9003, "File upload failed");

    private final int code;
    private final String message;
}
