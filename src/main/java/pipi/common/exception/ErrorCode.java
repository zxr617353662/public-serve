package pipi.common.exception;

/**
 * 2019/12/3 created by zado
 */
public interface ErrorCode {
    /**
     * 错误类型
     */
    String getType();

    /**
     * 错误码
     * <p>
     * 一般可以用4位的数字来进行表示
     */
    String getCode();

    /**
     * 错误内容
     * <p>
     * 支持形式:
     * 1. 简单错误. eg: some error happened
     * 2. 单参数类型 eg: error:{0} happened
     *
     * @see java.text.MessageFormat 支持的传参格式
     */
    String getDesc();
}
