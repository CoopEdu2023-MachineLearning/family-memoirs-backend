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

    ARTICLE_NOT_FOUND(3001, "Article not found"),

    IMAGE_NOT_FOUND(4001, "Image not found"),
    IMAGE_LOAD_FAILED(4002, "Image load failed"),

    AUDIO_NOT_FOUND(5001, "Audio not found"),
    AUDIO_LOAD_FAILED(5002, "Audio load failed"),

    TELLER_NOT_FOUND(6001, "Teller not found"),

    TAG_NOT_FOUND(7001, "Tag not found"),

    AVATAR_NOT_FOUND(8001, "Avatar not found"),
    AVATAR_LOAD_FAILED(8002, "Avatar load failed"),
    ;

    private final int code;
    private final String message;
}
