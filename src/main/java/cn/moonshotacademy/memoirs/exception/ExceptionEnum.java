package cn.moonshotacademy.memoirs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    MISSING_PARAMETERS(1001, "Missing required parameters"),
    USERNAME_ALREADY_EXISTS(1002, "Username already exists"),
    INVALID_CREDENTIALS(1003, "Invalid username or password");

    private final int code;
    private final String message;
}
