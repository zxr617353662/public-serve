package pipi.common.exception;


/**
 * 2019/12/4 created by zado
 */
public enum TSError implements ErrorCode {
    LOGIN_ERROR(            "0001", "登陆失败"),

    ;

    private final String code;
    private final String desc;

    TSError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getType() {
        return "ts";
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
