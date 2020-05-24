package com.ziroom.zftools.exception;

import com.ziroom.zftools.enums.ExceptionCode;

public class BaseException extends RuntimeException {

    // 错误码
    private int code;
    // 信息
    private String message;
    // 标志位
    private boolean flag = true;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BaseException() {
    }

    public BaseException(ExceptionCode resultCode) {
        this(resultCode.code);
    }

    public BaseException(ExceptionCode resultCode, String arg) {
        this(resultCode.code, arg);
    }

    public BaseException(int code) {
        super("ErrorCode:" + code);
        this.code = code;
        this.flag = true;
        this.message = "";
    }

    public BaseException(int code, String arg) {
        super("ErrorCode:" + code + " - " + arg);
        this.code = code;
        this.flag = true;
        this.message = arg;
    }


}
