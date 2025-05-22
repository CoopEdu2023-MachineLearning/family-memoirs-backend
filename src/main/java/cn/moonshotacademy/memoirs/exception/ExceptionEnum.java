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
    WRONG_PASSWORD(2003, "Wrong password"),

    EMAIL_NOT_FOUND(3001, "Email not found"),
    EMAIL_REQEUST_FAILED(3002, "Email request failed"),
    ;

    private final int code;
    private final String message;
}
