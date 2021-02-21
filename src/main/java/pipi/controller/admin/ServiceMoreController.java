package pipi.controller.admin;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pipi.model.ResponseStmt;
import pipi.model.article.Article;
import pipi.model.service.ServiceList;
import pipi.service.ArticleService;
import pipi.service.SerService;

import java.util.List;

@Controller
public class ServiceMoreController {

    @Autowired
    SerService serService;
    @Autowired
    ArticleService articleService;

    @ResponseBody
    @RequestMapping(
            value = "/add-service-name",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseStmt serviceManagementPage(
            @RequestParam("name") String name
    ){
        serService.addserviceName(name);
        return ResponseStmt.ADD_OK;
    }

    @ResponseBody
    @RequestMapping(
            value = "/service-list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ServiceList> serviceList(Model model){

        List<ServiceList> list = serService.serviceList();
        model.addAttribute("serList", list);
        return list;
    }
    @ResponseBody
    @RequestMapping(
            value = "/open-service",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseStmt openUse(
            @RequestParam("id") Long id,
            @RequestParam("state") String state

    ){
        serService.findById(id,state);
        if (state.equals("OPEN")){
            return ResponseStmt.OPEN_OK;
        }else {
            return ResponseStmt.CLOSE_OK;
        }
    }

    @ResponseBody
    @RequestMapping(
            value = "/del-service",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseStmt delSer(
            @RequestParam("id") Long id
    ){
        serService.delSer(id);
            return ResponseStmt.DEL_OK;
    }
}
