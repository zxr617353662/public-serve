package pipi.controller.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipi.model.ResponseStmt;
import pipi.model.article.AddArticleData;
import pipi.model.article.Article;
import pipi.model.notice.AddNoticeData;
import pipi.model.notice.Notice;
import pipi.service.NoticeService;

import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    NoticeService noticeService;


    @ResponseBody
    @RequestMapping(value = "/add_notice",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStmt addNotice(
            @RequestBody AddNoticeData addArticleData){
        noticeService.addNotice(addArticleData);
        return ResponseStmt.ADD_OK;
    }

    @ResponseBody
    @RequestMapping(value = "/notice_list",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Notice> listNotice(Model model){
        List<Notice> listNotice = noticeService.listNotice();
        model.addAttribute("listNotice",listNotice);
        return listNotice;
    }

    @ResponseBody
    @RequestMapping(value = "/notice_open",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStmt showNotice(@RequestParam("id")Long id,
                                   @RequestParam("state")String state
                                   ){

        noticeService.showNotice(id,state);
        return ResponseStmt.OPEN_OK;
    }

    @ResponseBody
    @RequestMapping(value = "/get_notice",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Notice getNotice(
            @RequestParam("id") Long id
    ){
        return noticeService.getNotice(id);
    }

    @ResponseBody
    @RequestMapping(value = "/update_notice",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseStmt updateNotice(
            @RequestBody AddNoticeData addNoticeData
    ){
        noticeService.updateNotice(addNoticeData);
        return ResponseStmt.UPDATE_OK;
    }

    @ResponseBody
    @RequestMapping(value = "/del_notice",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseStmt delNotice(
            @RequestParam("id") Long id
    ){
        noticeService.delNotice(id);
        return ResponseStmt.DEL_OK;
    }
}
