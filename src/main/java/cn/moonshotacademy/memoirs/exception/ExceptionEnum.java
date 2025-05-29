package cn.moonshotacademy.memoirs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    MISSING_PARAMETERS(1001, "Missing parameters"),
    ILLEGAL_PARAMETERS(1002, "Illegal parameters"),

    USER_EXISTS(2001, "User exists"),
    USER_NOT_FOUND(2002, "User not found"),
    WRONG_PASSWORD(2003, "Wrong password"),
    TELLER_CREATE_FAILED(2004, "Teller create failed"),
    FILE_UPLOAD_FILE(2005, "File upload failed"),
    ;

    private final int code;
    private final String message;
}
