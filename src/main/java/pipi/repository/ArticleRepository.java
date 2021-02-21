package pipi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pipi.mapper.ArticleFileMapper;
import pipi.mapper.ArticleMapper;
import pipi.mapper.SerMapper;
import pipi.model.article.AddArticleData;
import pipi.model.article.Article;
import pipi.model.article.ArticleFile;
import pipi.model.service.ServiceList;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class ArticleRepository {
    @Autowired
    private ArticleMapper articleMapper;

  @Autowired
    private ArticleFileMapper articleFileMapper;


    public void addArticle(Article article) {
        articleMapper.insert(article);
    }

    public List<Article> findAll(Long serId) {
        return articleMapper.findAllBySerId(serId);
    }

    public List<Article> articleAllList() {

        return articleMapper.selectAll();
    }

    public void delArticle(Long id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    public Article findById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public void update(Article byId) {
        articleMapper.updateByPrimaryKeySelective(byId);
    }

    public List<Article> findAllbyOpen(Long id, String open) {
        return articleMapper.findAllbyOpen(id,open);
    }

    public List<Article> findAllbyOpenNolimit(Long id, String open) {
        return articleMapper.findAllbyOpenNolimit(id,open);
    }

    public void addArticleFile(ArticleFile articleFile) {
        articleFileMapper.insert(articleFile);
    }

    public ArticleFile findAllDiff() {
        return articleFileMapper.findAllDiff();
    }

    public ArticleFile findFileByArtId(Long id) {
        return articleFileMapper.findByArtId(id);
    }

    public void articlehot(Article byId) {
        articleMapper.updateByPrimaryKeySelective(byId);
    }

    public List<Article> findByhot() {
        return articleMapper.findByhot("OPEN");
    }

    public List<Article> findAllOrderById(String open) {
        return articleMapper.findAllOrderById(open);
    }

    public Integer findNumBySerId(Long id) {
        return articleMapper.findNumBySerId(id,"OPEN");
    }
}
