package pipi.model.message;

import lombok.Data;
import pipi.model.TableNames;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = TableNames.MESSAGE_LIST)
public class MessageList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromId;
    private Long intoId;
    private String fromRole;
    private Long unread;
    private String newMessage;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
