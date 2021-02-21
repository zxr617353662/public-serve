package pipi.service;

import org.springframework.web.multipart.MultipartFile;
import pipi.model.article.*;
import pipi.model.service.SerSize;
import pipi.model.service.ServiceList;

import java.util.List;

public interface ArticleService {

    Long addArticle(AddArticleData addArticleData);

    List<Article> articleList(Long serId);

    List<Article> articleAllList();

    void delArticle(Long id);

    void useArticle(Long id, String state);

    Article getArticle(Long id);

    void updArticle(UpdateArticleData updateArticleData);

    void addArticleFile(ArticleFile articleFile);

    ArticleFile findAllDiff();

    ArticleAndFileData findArtAndFile( Long id);

    void articlehot(Long id);

    List<AddArticleData> findByhot();

    List<PushArticleList> pushArticleListList();

    List<SerSize> articleEchars();


}
