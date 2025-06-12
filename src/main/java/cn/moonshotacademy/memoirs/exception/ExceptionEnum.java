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
    INVALID_INVITATION_CODE(2011, "Invalid invitation code"),
    INVALID_VERIFICATION_CODE(2012, "Invalid verification code"),
    INVALID_USER_ID(2013, "无效的用户ID"),

    ARTICLE_NOT_FOUND(3001, "Article not found"),

    IMAGE_NOT_FOUND(4001, "Image not found"),
    IMAGE_LOAD_FAILED(4002, "Image load failed"),

    NO_VERIFIED_ARTICLE(4003, "No verified article"),
    NO_UNVERIFIED_ARTICLE(4004, "No unverified article"),

    AUDIO_NOT_FOUND(5001, "Audio not found"),
    AUDIO_LOAD_FAILED(5002, "Audio load failed"),

    TELLER_NOT_FOUND(6001, "Teller not found"),
    TELLER_CREATE_FAILED(6002, "Teller create failed"),
    INVALID_RELATION(6003, "无效的亲属关系"),
    MUST_CREATE_SELF_FIRST(6004, "必须先创建‘我自己’的讲述者"),

    TAG_NOT_FOUND(7001, "Tag not found"),

    AVATAR_NOT_FOUND(8001, "Avatar not found"),
    AVATAR_LOAD_FAILED(8002, "Avatar load failed"),

    NULL_FILENAME(9001, "File name cannot be null"),
    FILE_UPLOAD_ERROR(9002, "File upload failed"),
    FAIL_UPLOAD(9003, "Fail to write to the Database"),
    NULL_FILELIST(9004, "Cannot upload NULL filelist"),
    TYPE_NOTALLOW(9005, "This file type is not allowed"),
    FILE_NOT_FOUND(9006, "File not found"),
    EMPTY_FILE(9007, "Empty file"),
    SEARCH_ERROR(9008, "Error when searching documents")
    ;

    private final int code;
    private final String message;
}
