package pipi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pipi.model.notice.AddNoticeData;
import pipi.model.notice.Notice;
import pipi.repository.NoticeRepository;
import pipi.service.NoticeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public void addNotice(AddNoticeData addArticleData) {
        Notice notice = new Notice();
        notice.setAuthor(addArticleData.getAuthor());
        notice.setNoticeContent(addArticleData.getNoticeContent());
        notice.setNoticeTitle(addArticleData.getNoticeTitle());
        notice.setState("CLOSE");
        notice.setCreateTime(LocalDateTime.now());
        noticeRepository.addNotice(notice);
    }

    @Override
    public List<Notice> listNotice() {
        return noticeRepository.listNotice();
    }

    @Override
    public List<Notice> listNoticeFindByTitle(String title) {
        List<Notice> noticeList = noticeRepository.listNoticeFindByTitle(title);
        return noticeList;
    }

    @Override
    public void showNotice(Long id, String state) {
       Notice notice = noticeRepository.findById(id);
       notice.setState(state);
       notice.setUpdateTime(LocalDateTime.now());
       noticeRepository.update(notice);
    }

    @Override
    public Notice getNotice(Long id) {
        return noticeRepository.findById(id);
    }

    @Override
    public void updateNotice(AddNoticeData addNoticeData) {
        Notice byId = noticeRepository.findById(addNoticeData.getId());
        byId.setNoticeContent(addNoticeData.getNoticeContent());
        byId.setNoticeTitle(addNoticeData.getNoticeTitle());
        byId.setAuthor(addNoticeData.getAuthor());
        byId.setUpdateTime(LocalDateTime.now());
        noticeRepository.update(byId);
    }

    @Override
    public void delNotice(Long id) {
        noticeRepository.delNotice(id);
    }
}
