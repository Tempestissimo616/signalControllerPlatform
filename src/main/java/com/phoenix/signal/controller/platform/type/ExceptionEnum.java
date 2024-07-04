package com.phoenix.signal.controller.platform.type;

public enum ExceptionEnum {
    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "未经授权的访问"),
    NOT_FOUND(404, "资源未找到"),
    CONFLICT_EXCEPTION(409, "资源已存在"),
    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),

    ;

    private int code;
    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
