package pipi.model.message;

import lombok.Data;

@Data
public class MessageListData {
    private String name;
    private String content;
    private String unread;
    private String updateTime;
}
