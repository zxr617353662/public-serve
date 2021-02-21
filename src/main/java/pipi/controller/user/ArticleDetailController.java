package pipi.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pipi.model.ResponseStmt;
import pipi.model.article.Article;
import pipi.model.article.ArticleAndFileData;
import pipi.model.service.SerSize;
import pipi.service.ArticleService;

import java.util.List;


@Controller
public class ArticleDetailController {
    @Autowired
    ArticleService articleService;
    @ResponseBody
    @RequestMapping(value = "/article_detail" ,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ArticleAndFileData articleDetail(
            @RequestParam("id") Long id
    ){
        ArticleAndFileData articleAndFileData = articleService.findArtAndFile(id);
        return articleAndFileData;
    }

    @ResponseBody
    @RequestMapping(value = "/article_hot" ,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ResponseStmt articlehot(
            @RequestParam("id") Long id
    ){
        articleService.articlehot(id);
        return ResponseStmt.UPDATE_OK;
    }
    @ResponseBody
    @RequestMapping(value = "/article_echars" ,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public List<SerSize> articleEchars(){
        List<SerSize> serSizes = articleService.articleEchars();
        return serSizes;
    }


}
