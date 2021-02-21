package pipi.model;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseStmt {
    ADD_OK("添加成功"),
    OPEN_OK("启用成功"),
    CLOSE_OK("关闭成功"),
    DEL_OK("删除成功"),
    UPDATE_OK("更新成功"),
    PROCESS_OK("处理成功"),





    ;

    private final String val;

    ResponseStmt() {
        this(null);
    }

    ResponseStmt(String val) {
        this.val = val;
    }

    // 通过@JsonValue序列化成String
    // jackson converter
    @JsonValue
    @SuppressWarnings("unused")
    public String val() {
        return val == null ? name() : val;
    }
}
