package pipi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pipi.model.ResponseStmt;
import pipi.model.article.AddArticleData;
import pipi.model.article.Article;
import pipi.model.article.ArticleFile;
import pipi.model.article.UpdateArticleData;
import pipi.model.service.ServiceList;
import pipi.service.ArticleService;
import pipi.service.SerService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    SerService serService;
    @ResponseBody
    @RequestMapping(value = "/add_article",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Long addArticle(
            @RequestBody AddArticleData addArticleData
            ){
        Long aLong = articleService.addArticle(addArticleData);
        return aLong;
    }
    @ResponseBody
    @RequestMapping(value = "/del_article",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStmt delArticle(
            @RequestParam("id") Long id){
        articleService.delArticle(id);
        return ResponseStmt.DEL_OK;
    }

    @ResponseBody
    @RequestMapping(value = "/use_article",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseStmt useArticle(
            @RequestParam("id") Long id,
            @RequestParam("state") String state
            ){
        articleService.useArticle(id,state);
        if ("CLOSE".equals(state)){
            return ResponseStmt.CLOSE_OK;
        }else if ("OPEN".equals(state)){
            return ResponseStmt.OPEN_OK;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/get_article",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Article getArticle(
            @RequestParam("id") Long id
    ){
        return articleService.getArticle(id);
    }

    @ResponseBody
    @RequestMapping(value = "/upd_article",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStmt updArticle(
            @RequestBody UpdateArticleData updateArticleData){
        articleService.updArticle(updateArticleData);
        return ResponseStmt.UPDATE_OK;
    }

    @RequestMapping(value = "/uplo_file",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("articleId")String id,
            Model model
    ){
        List<ServiceList> lista = serService.serviceList();
        model.addAttribute("serList", lista);
        model.addAttribute("function",3);
        List<Article> articles = articleService.articleAllList();
        model.addAttribute("articles",articles);
        List<ServiceList> listSelect = serService.serviceListLi();
        model.addAttribute("listSelect",listSelect);
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        ArticleFile articleFilenew = new ArticleFile();
        ArticleFile articleFile = articleService.findAllDiff();
        if (articleFile == null){
            split[0] = split[0] + 1+"";
            originalFilename = split[0]+"."+split[1];
            articleFilenew.setFileName(originalFilename);
        }else {
            Long num = articleFile.getId() + 1;
            originalFilename = split[0] = split[0] + num +"."+split[1];
            articleFilenew.setFileName(originalFilename);
        }
        //定义上传文件保存路径
        String beginPath = "E:\\file";
        //新建文件
        assert originalFilename != null;
        File filePath = new File(beginPath,originalFilename);

        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(beginPath + File.separator + originalFilename));
        }catch (IOException e){
            e.printStackTrace();
        }
        articleFilenew.setDownTime(0L);
        articleFilenew.setArtId(Long.parseLong(id));
        articleFilenew.setFileName(originalFilename);
        articleFilenew.setFilePath(beginPath + File.separator + originalFilename);
        articleFilenew.setCreateTime(LocalDateTime.now());
        articleService.addArticleFile(articleFilenew);
        return "admin/service_management";
    }
}
