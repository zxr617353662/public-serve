package pipi.service.impl;

import io.lettuce.core.support.caching.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import pipi.model.TimeChangeUtil;
import pipi.model.article.*;
import pipi.model.service.SerSize;
import pipi.model.service.ServiceList;
import pipi.model.service.ServiceState;
import pipi.repository.ArticleRepository;
import pipi.repository.SerRepository;
import pipi.service.ArticleService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ArticleServiceImpl implements ArticleService, Serializable {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    SerRepository serRepository;
//
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    /**注入springboot自动配置好的redistemplate**/

    @Override
    public Long addArticle(AddArticleData addArticleData) {

        Article article = new Article();
        article.setArticleContent(addArticleData.getArticleContent());
        article.setArticleName(addArticleData.getArticleName());
        article.setServiceId(addArticleData.getServiceId());
        article.setAuthorName(addArticleData.getAuthorName());
        article.setHot(0L);
        article.setCreateTime(LocalDateTime.now());
        article.setState("CLOSE");
        articleRepository.addArticle(article);
        return article.getId();
    }

    @Override
    public List<Article> articleList(Long serId) {
        return articleRepository.findAll(serId);
    }

    @Override
    public List<Article> articleAllList() {
        return articleRepository.articleAllList();
    }

    @Override
    public void delArticle(Long id) {
        articleRepository.delArticle(id);
    }

    @Override
    public void useArticle(Long id, String state) {
        Article byId = articleRepository.findById(id);
        byId.setState(state);
        articleRepository.update(byId);
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public void updArticle(UpdateArticleData updateArticleData) {
        Article byId = articleRepository.findById(updateArticleData.getId());
        byId.setArticleContent(updateArticleData.getArticleContent());
        byId.setArticleName(updateArticleData.getArticleName());
        byId.setAuthorName(updateArticleData.getAuthorName());
        byId.setUpdateTime(LocalDateTime.now());
        articleRepository.update(byId);
    }

    @Override
    public void addArticleFile(ArticleFile articleFile) {
        articleRepository.addArticleFile(articleFile);
    }

    @Override
    public ArticleFile findAllDiff() {
        return articleRepository.findAllDiff();
    }

    @Override
    public ArticleAndFileData findArtAndFile(Long id) {
        Article byId = articleRepository.findById(id);
        ArticleAndFileData articleAndFileData = new ArticleAndFileData();
        articleAndFileData.setArticle(byId);
        ArticleFile articleFile = articleRepository.findFileByArtId(id);
        articleAndFileData.setArticleFile(articleFile);
        return articleAndFileData;
    }

    @Override
    public void articlehot(Long id) {
        Article byId = articleRepository.findById(id);
        byId.setHot(byId.getHot() + 1);
        byId.setUpdateTime(LocalDateTime.now());
        articleRepository.articlehot(byId);
    }

    @Override
    public List<AddArticleData> findByhot() {
//        List<Article> byhot = articleRepository.findByhot();
//        List<AddArticleData> addArticleDataList = new ArrayList<>();
//        for (Article article : byhot) {
//            AddArticleData addArticleData = new AddArticleData();
//            addArticleData.setId(article.getId());
//            addArticleData.setArticleContent(article.getArticleContent());
//            addArticleData.setArticleName(article.getArticleName());
//            addArticleData.setAuthorName(article.getAuthorName());
//            String time = changeTime(article.getCreateTime());
//            addArticleData.setCreateTime(time);
//            addArticleData.setServiceId(article.getServiceId());
//            addArticleDataList.add(addArticleData);
//        }
//
//        return addArticleDataList;


        //高并发条件下,容易有问题:缓存穿透


        //字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<AddArticleData> addArticleDataList = (List<AddArticleData>) redisTemplate.opsForValue().get("allStudent");
        //双重检测
        if(null == addArticleDataList) {
            synchronized (this) {
                //从redis获取一下
                addArticleDataList = (List<AddArticleData>) redisTemplate.opsForValue().get("allStudent");
                if (null == addArticleDataList) {
                    List<AddArticleData> articleDataList = new ArrayList<>();
                    System.out.println("没找到");
                    List<Article> byhot = articleRepository.findByhot();
                    for (Article article : byhot) {
                        AddArticleData addArticleData = new AddArticleData();
                        addArticleData.setId(article.getId());
                        addArticleData.setArticleContent(article.getArticleContent());
                        addArticleData.setArticleName(article.getArticleName());
                        addArticleData.setAuthorName(article.getAuthorName());
                        String time = TimeChangeUtil.changeTime(article.getCreateTime());
                        addArticleData.setCreateTime(time);
                        addArticleData.setServiceId(article.getServiceId());
                        articleDataList.add(addArticleData);
                        redisTemplate.opsForValue().set("allStudent", articleDataList,200, TimeUnit.SECONDS);
                        return articleDataList;
                    }
                } else {
                    System.out.println("找到了");
                    return addArticleDataList;
                }
            }
        }else {
            System.out.println("找到了");
            return addArticleDataList;
        }
        return null;
    }

    @Override
    public List<PushArticleList> pushArticleListList() {
        List<PushArticleList> articleLists = new ArrayList<>();
        List<Article> article = articleRepository.findAllOrderById("OPEN");
        for (Article art : article) {
            ServiceList byId = serRepository.findById(art.getServiceId());
            PushArticleList pushArticleList = new PushArticleList();
            pushArticleList.setArticleContent(art.getArticleContent());
            pushArticleList.setArticleId(art.getId());
            pushArticleList.setArticleName(art.getArticleName());
            pushArticleList.setAuthorName(art.getAuthorName());
            String time = TimeChangeUtil.changeTime(art.getCreateTime());
            pushArticleList.setCreateTime(time);
            pushArticleList.setSerName(byId.getName());
            articleLists.add(pushArticleList);
        }
        return articleLists;
    }


    @Override
    public List<SerSize> articleEchars() {
        List<SerSize> serSizes = new ArrayList<>();
        List<ServiceList> allOpen = serRepository.findAllOpen(ServiceState.OPEN);
        for (ServiceList serviceList : allOpen) {
            SerSize serSize = new SerSize();
            Integer num = articleRepository.findNumBySerId(serviceList.getId());
            serSize.setNum(num);
            serSize.setSerName(serviceList.getName());
            serSizes.add(serSize);
        }
        return serSizes;
    }
}
