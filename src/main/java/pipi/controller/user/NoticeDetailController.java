package pipi.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipi.model.ResponseStmt;
import pipi.model.notice.AddNoticeData;
import pipi.model.notice.Notice;
import pipi.model.notice.NoticeData;
import pipi.service.NoticeService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeDetailController {
    @Autowired
    NoticeService noticeService;



    @ResponseBody
    @RequestMapping(value = "/notice_listDet",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Notice> listNotice(Model model){
        List<Notice> listNotice = noticeService.listNotice();
        model.addAttribute("listNotice",listNotice);
        return listNotice;
    }


    @ResponseBody
    @RequestMapping(value = "/get_noticeDet",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public NoticeData getNotice(
            @RequestParam("id") Long id
    ){
        Notice notice = noticeService.getNotice(id);
        NoticeData noticeData = new NoticeData();
        noticeData.setAuthor(notice.getAuthor());
        String time = changeTime(notice.getCreateTime());
        noticeData.setCreateTime(time);
        noticeData.setId(notice.getId());
        noticeData.setNoticeContent(notice.getNoticeContent());
        noticeData.setNoticeTitle(notice.getNoticeTitle());
        return noticeData;
    }

    @RequestMapping(value = "/notice_listPage")
    public String listNoticePage(Model model){
        List<Notice> listNotice = noticeService.listNotice();
        List<NoticeData> listNoticeData =new ArrayList<>();
        for (Notice notice : listNotice) {
            NoticeData noticeData = new NoticeData();
            noticeData.setAuthor(notice.getAuthor());
            String time = changeTime(notice.getCreateTime());
            noticeData.setCreateTime(time);
            noticeData.setId(notice.getId());
            noticeData.setNoticeContent(notice.getNoticeContent());
            noticeData.setNoticeTitle(notice.getNoticeTitle());
            listNoticeData.add(noticeData);
        }
        model.addAttribute("listNotice",listNoticeData);
        return "user/notice_list";
    }
    public String changeTime(LocalDateTime dateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = dateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateformat.format(date);
        return time;
    }
}
