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

    NULL_FILENAME(4001, "File name cannot be null"),
    FAIL_UPLOAD(4003, "Fail to write to the Database"),
    NULL_FILELIST(4004, "Cannot upload NULL filelist"),
    TYPE_NOTALLOW(4005, "This file type is not allowed"),
    FILE_NOT_FOUND(3006, "File not found")
    ;

    private final int code;
    private final String message;
}
