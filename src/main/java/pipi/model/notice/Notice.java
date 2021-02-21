package pipi.model.notice;

import lombok.Data;
import pipi.model.TableNames;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = TableNames.NOTICE)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private String author;
    private String state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
