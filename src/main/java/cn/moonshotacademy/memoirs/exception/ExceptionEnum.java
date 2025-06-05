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
    INVALID_USER_ID(2006, "无效的用户ID"),
    INVALID_RELATION(2007, "无效的亲属关系"),
    MUST_CREATE_SELF_FIRST(2008, "必须先创建‘我自己’的讲述者");
    ;

    private final int code;
    private final String message;
}
