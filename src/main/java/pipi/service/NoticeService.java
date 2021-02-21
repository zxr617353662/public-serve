package pipi.service;

import pipi.model.notice.AddNoticeData;
import pipi.model.notice.Notice;

import java.util.List;

public interface NoticeService {
    void addNotice(AddNoticeData addArticleData);

    List<Notice> listNotice();

    List<Notice> listNoticeFindByTitle(String title);

    void showNotice(Long id, String state);

    Notice getNotice(Long id);

    void updateNotice(AddNoticeData addNoticeData);

    void delNotice(Long id);
}
