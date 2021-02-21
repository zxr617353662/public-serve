package pipi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pipi.mapper.NoticeMapper;
import pipi.mapper.SerMapper;
import pipi.model.notice.Notice;
import pipi.model.service.ServiceList;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class NoticeRepository {
    @Autowired
    private NoticeMapper noticeMapper;


    public void addNotice(Notice notice) {
        noticeMapper.insert(notice);
    }

    public List<Notice> listNotice() {
        return noticeMapper.listNotice();
    }

    public List<Notice> listNoticeFindByTitle(String title) {
        return noticeMapper.listNoticeFindByTitle("%"+title+"%");
    }

    public Notice findById(Long id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    public void update(Notice notice) {
        noticeMapper.updateByPrimaryKeySelective(notice);
    }

    public void delNotice(Long id) {
        noticeMapper.deleteByPrimaryKey(id);
    }
}
