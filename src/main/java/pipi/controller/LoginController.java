package pipi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipi.model.article.AddArticleData;
import pipi.model.article.Article;
import pipi.model.article.PushArticleList;
import pipi.model.file.FileManagement;
import pipi.model.file.FileManagementUserData;
import pipi.model.login.Login;
import pipi.model.login.Role;
import pipi.model.message.MessageList;
import pipi.model.notice.Notice;
import pipi.model.service.ServiceAritcle;
import pipi.model.service.ServiceList;
import pipi.service.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    SerService serService;
    @Autowired
    ArticleService articleService;
    @Autowired
    FileService fileService;

    //登陆
    @RequestMapping(
            value = "/login-into", method = RequestMethod.POST
    )
    public String loginInto(
            @RequestParam("username") String name,
            @RequestParam("password") String password,
            @RequestParam("role") Role role,
            HttpServletResponse response,
            Model model
    ) {
        Cookie cookie = new Cookie("username", name);
        cookie.setPath("/");
        Login b = loginService.login(name, password, role);
        Cookie cookie1 = new Cookie("id", b.getId() + "");
        if (b.getRole().equals("USER")) {
            List<AddArticleData> articleList = articleService.findByhot();
            List<ServiceAritcle> list = serService.findAllArticle(1, 5);
            List<PushArticleList> pushArticleListList = articleService.pushArticleListList();
            List<Notice> listNotice = noticeService.listNotice();
            model.addAttribute("listNotice", listNotice.get(listNotice.size() - 1));
            response.addCookie(cookie);
            response.addCookie(cookie1);
            model.addAttribute("function", 1);
            model.addAttribute("id", b.getId());
            model.addAttribute("artList", list);
            model.addAttribute("articleHotList", articleList);
            model.addAttribute("pushArticleListList", pushArticleListList);
            return "user/homePage";
        } else if (b.getRole().equals("ADMIN")) {
            model.addAttribute("function", 1);
            model.addAttribute("id", b.getId());
            response.addCookie(cookie);
            response.addCookie(cookie1);
            return "admin/index";
        }
        return null;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //首页
    @GetMapping("/admin/index")
    public String adminIndex(Model model) {
        List<AddArticleData> articleList = articleService.findByhot();
        List<ServiceAritcle> list = serService.findAllArticle(1, 5);
        List<PushArticleList> pushArticleListList = articleService.pushArticleListList();
        List<Notice> listNotice = noticeService.listNotice();
        model.addAttribute("listNotice", listNotice.get(listNotice.size() - 1));
        model.addAttribute("artList", list);
        model.addAttribute("articleHotList", articleList);
        model.addAttribute("pushArticleListList", pushArticleListList);
        model.addAttribute("function", 1);
        return "admin/index";
    }

    //服务大全
    @RequestMapping(
            value = "/service-total", method = RequestMethod.GET
    )
    public String serviceMorePage(Model model) {
        model.addAttribute("function", 2);
        List<ServiceList> list = serService.serviceListLi();
        model.addAttribute("serList", list);
        return "admin/service_total";
    }

    //服务管理
    @RequestMapping(
            value = "/service-management", method = RequestMethod.GET
    )
    public String servicManagementPage(Model model, @RequestParam(value = "id", required = false) Long serId
    ) {
        model.addAttribute("function", 3);
        List<ServiceList> listSelect = serService.serviceListLi();
        model.addAttribute("listSelect", listSelect);


        List<ServiceList> list = serService.serviceList();
        model.addAttribute("serList", list);
        if (serId == null || serId == 1) {
            List<Article> articles = articleService.articleAllList();
            model.addAttribute("articles", articles);
        } else {
            List<Article> articles = articleService.articleList(serId);
            ServiceList byIdNoSt = serService.findByIdNoSt(serId);
            model.addAttribute("articles", articles);
            model.addAttribute("serId", serId);
            model.addAttribute("serName", byIdNoSt);
        }
        return "admin/service_management";
    }

    //公告管理
    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String noticePage(Model model, @RequestParam(value = "title", required = false) String title) throws UnsupportedEncodingException {
        model.addAttribute("function", 4);
        if (title == null) {
            List<Notice> listNotice = noticeService.listNotice();
            model.addAttribute("listNotice", listNotice);
        } else {
            String name = java.net.URLDecoder.decode(title, "UTF-8");//需要抛异常
            List<Notice> listNotice = noticeService.listNoticeFindByTitle(name);
            model.addAttribute("listNotice", listNotice);
        }
        return "admin/notice";
    }

    //用户管理
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Model model, @RequestParam(value = "name", required = false) String name) throws UnsupportedEncodingException {
        model.addAttribute("function", 5);
        model.addAttribute("count", loginService.findCountUser());
        if (name == null) {
            List<Login> userList = loginService.findAll();
            model.addAttribute("userList", userList);
        } else {
            String findName = java.net.URLDecoder.decode(name, "UTF-8");//需要抛异常
            List<Login> userList = loginService.findByName(findName);
            model.addAttribute("userList", userList);
        }

        return "admin/user";
    }

    //文件管理列表
    @RequestMapping(value = "/message_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String articleList(Model model) {

        model.addAttribute("function", 6);
        List<FileManagementUserData> lists = fileService.fileList();
        model.addAttribute("fileList", lists);
        return "admin/message_list";
    }


    @RequestMapping(value = "/article_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String articleList(@RequestParam(value = "id", required = false) Long serId,
                              Model model) {
        if (serId == null) {
            List<Article> articles = articleService.articleAllList();
            model.addAttribute("articles", articles);
        } else {
            List<Article> articles = articleService.articleList(serId);
            ServiceList byIdNoSt = serService.findByIdNoSt(serId);
            model.addAttribute("articles", articles);
            model.addAttribute("serId", serId);
            model.addAttribute("serName", byIdNoSt.getName());
        }
        return "admin/service_management";
    }

    //用户主页
    @RequestMapping(value = "/user/index", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String userIndex(Model model) {
        model.addAttribute("function", 1);
        List<AddArticleData> articleList = articleService.findByhot();
        List<ServiceAritcle> list = serService.findAllArticle(1, 4);
        List<PushArticleList> pushArticleListList = articleService.pushArticleListList();
        List<Notice> listNotice = noticeService.listNotice();
        model.addAttribute("listNotice", listNotice.get(listNotice.size() - 1));
        model.addAttribute("artList", list);
        model.addAttribute("articleHotList", articleList);
        model.addAttribute("pushArticleListList", pushArticleListList);

        return "user/homePage";
    }

    //用户服务大全
    @RequestMapping(value = "/user/serviceTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String userServiceTotal(Model model,
                                   @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "30") Integer pageSize) {
        model.addAttribute("function", 2);
        List<AddArticleData> articleList = articleService.findByhot();
        List<ServiceAritcle> list = serService.findAllArticle(currentPage, pageSize);
        List<ServiceAritcle> list1 = serService.findAllArticle1(currentPage, pageSize);
        List<PushArticleList> pushArticleListList = articleService.pushArticleListList();
        model.addAttribute("artList", list);
        model.addAttribute("artList1", list1);
        model.addAttribute("articleHotList", articleList);
        model.addAttribute("pushArticleListList", pushArticleListList);
        return "user/service_total";
    }
}
//    public static void main(String[] args) {
//        HashMap<Integer,String> stringHashMap = new HashMap<>();
//        Thread thread1 = new Thread();
//        Thread thread2 = new Thread();
//        Thread thread3 = new Thread();
//        Thread thread4 = new Thread();
//
//    }
//public static void main(String[] args) {
////    MyThread myThread = new MyThread();
////    myThread.run();
//    MyTask myTask = new MyTask();
//    //它表达的是一个任务,需要一个线程来完成
//    new Thread(myTask).start();
//}
//
//}
//class MyThread extends Thread{
//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName()+"running");
//    }
//}
//class MyTask implements Runnable{
//
//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName()+"running");
//        String a= "asdfgh";
//        String[] s= new String[a.length()];
//        for (int i = 0; i < a.length()-1; i++) {
//            System.out.println();
//        }
//
//    }
//}
//class MyTash2 implements Callable{
//
//    @Override
//    public Object call() throws Exception {
//        String a = "asddfef";
//        return null;
//    }
//}
