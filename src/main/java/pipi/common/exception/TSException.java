package pipi.common.exception;


import java.text.MessageFormat;

/**
 * 2019/12/3 created by zado
 */
public class TSException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String code;

    public TSException(ErrorCode errorCode) {
        this(errorCode, (Object[]) null);
    }

    public TSException(ErrorCode errorCode, Object... args) {
        super(format(errorCode, args));
        this.errorCode = errorCode;
        this.code = errorCode.getType() + "-" + errorCode.getCode();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getCode() {
        return code;
    }

    private static String format(ErrorCode errorCode, Object... args) {
        if (errorCode == null) {
            throw new IllegalArgumentException("ErrorCode cannot be null");
        }

        StringBuilder desc = new StringBuilder();
        if (args == null || args.length == 0) {
            desc.append(errorCode.getDesc());
        } else {
            MessageFormat format = new MessageFormat(errorCode.getDesc());
            desc.append(format.format(args));
        }

        return desc.toString();
    }
}
