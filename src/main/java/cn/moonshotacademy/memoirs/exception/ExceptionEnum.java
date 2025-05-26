package cn.moonshotacademy.memoirs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    MISSING_PARAMETERS(1001, "Missing parameters"),
    ILLEGAL_PARAMETERS(1002, "Illegal parameters"),

    USER_EXISTS(2001, "User exists"),
    USER_NOT_FOUND(2002, "User not found"),
    WRONG_PASSWORD(2003, "Wrong password"),

    EMAIL_NOT_FOUND(3001, "Email not found"),
    EMAIL_REQEUST_FAILED(3002, "Email request failed"),

    NO_VERIFIED_ARTICLE(4003, "No verified article"),
    NO_UNVERIFIED_ARTICLE(4004, "No unverified article"),
    ;

    private final int code;
    private final String message;
}
