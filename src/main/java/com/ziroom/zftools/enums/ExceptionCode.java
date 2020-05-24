package com.ziroom.zftools.enums;

public enum ExceptionCode {

    COMMON_ERROR(100000),
    NETWORK_ERROR(100001),
    TIMEOUT_ERROR(100002),

    DB_CONNECT_ERROR(200000),
    DB_QUERY_ERROR(200001),
    DB_UPDATE_ERROR(200002),
    DB_INSERT_ERROR(200003),
    DB_DELETE_ERROR(200004),

    USER_LOGON_ERROR(300000),
    USER_LOGIN_ERROR(300001),
    USER_HAS_LOGON_ERROR(300002),
    USER_NOT_LOGON_ERROR(300003),
    USER_PASSWORD_MISMATCH(300004),

    INPUT_ERROR(999999);

    public final int code;

    ExceptionCode(int code) {
        this.code = code;
    }

}
