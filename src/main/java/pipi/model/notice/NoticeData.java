package pipi.model.notice;

import lombok.Data;
import pipi.model.TableNames;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
public class NoticeData {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private String author;
    private String state;
    private String createTime;
}
