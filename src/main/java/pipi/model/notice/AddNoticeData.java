package pipi.model.notice;

import lombok.Data;

@Data
public class AddNoticeData {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private String author;
}
