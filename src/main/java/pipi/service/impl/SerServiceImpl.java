package pipi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pipi.model.article.AddArticleData;

import pipi.model.article.Article;
import pipi.model.service.ServiceAritcle;
import pipi.model.service.ServiceList;
import pipi.model.service.ServiceState;
import pipi.repository.ArticleRepository;
import pipi.repository.SerRepository;
import pipi.service.SerService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SerServiceImpl implements SerService {

    @Autowired
    SerRepository serRepository;
  @Autowired
  ArticleRepository articleRepository;

    @Override
    public void addserviceName(String name) {
        ServiceList serviceList = new ServiceList();
        serviceList.setName(name);
        serviceList.setState("CLOSE");
        serviceList.setCreateTime(LocalDateTime.now());
        serRepository.addserviceName(serviceList);
    }

    @Override
    public List<ServiceList> serviceList() {
        return serRepository.serviceList();
    }

    @Override
    public void findById(Long id,String state) {
        ServiceList byId = serRepository.findById(id);
        byId.setState(state);
        byId.setUpdateTime(LocalDateTime.now());
        serRepository.updateSer(byId);
    }

    @Override
    public void delSer(Long id) {
        serRepository.delSer(id);
    }

    @Override
    public ServiceList findByIdNoSt(Long serId) {
        return serRepository.findByIdNoSt(serId);
    }

    @Override
    public List<ServiceList> serviceListLi() {
        return serRepository.serviceListLi();
    }

    @Override
    public List<ServiceAritcle> findAllArticle(Integer currentPage,Integer pageSize) {
        List<ServiceList> list = serRepository.findAllOpen(ServiceState.OPEN);
        List<ServiceAritcle> serviceAritcleList = new ArrayList<>();
        if (list.size()!=0){
            for (ServiceList serviceList : list) {
                ServiceAritcle serviceAritcle = new ServiceAritcle();
                serviceAritcle.setId(serviceList.getId());
                serviceAritcle.setName(serviceList.getName());
                PageHelper.startPage(currentPage,pageSize);
                PageInfo<Article> articlePageInfo = new PageInfo<>(articleRepository.findAllbyOpen(serviceList.getId(),"OPEN"));
                List<Article> articleList = articlePageInfo.getList();
                List<Article> articleListsize = articleRepository.findAllbyOpenNolimit(serviceList.getId(),"OPEN");
                serviceAritcle.setSize(articleListsize.size());
                serviceAritcle.setPageNum(articlePageInfo.getPages());
                serviceAritcle.setPageInfo(articlePageInfo);
                if (articleList.size() != 0){
                    List<AddArticleData> addArticleData = new ArrayList<>();
                    for (Article addArticleDatum : articleList) {
                        AddArticleData addArticle = new AddArticleData();
                        addArticle.setId(addArticleDatum.getId());
                        addArticle.setArticleContent(addArticleDatum.getArticleContent());
                        addArticle.setArticleName(addArticleDatum.getArticleName());
                        addArticle.setAuthorName(addArticleDatum.getAuthorName());
                        ZoneId zoneId = ZoneId.systemDefault();
                        ZonedDateTime zdt = addArticleDatum.getCreateTime().atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
                        Date date = Date.from(zdt.toInstant());
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                        String time = dateformat.format(date);
                        addArticle.setCreateTime(time);
                        addArticle.setServiceId(addArticleDatum.getServiceId());
                        addArticleData.add(addArticle);
                    }
                    serviceAritcle.setArticleList(addArticleData);
                    serviceAritcleList.add(serviceAritcle);
                }
            }
        }
        return serviceAritcleList;
    }
    @Override
    public List<ServiceAritcle> findAllArticle1(Integer currentPage,Integer pageSize) {
        List<ServiceList> list = serRepository.findAllOpen1(ServiceState.OPEN);
        List<ServiceAritcle> serviceAritcleList = new ArrayList<>();
        if (list.size()!=0){
            for (ServiceList serviceList : list) {
                ServiceAritcle serviceAritcle = new ServiceAritcle();
                serviceAritcle.setId(serviceList.getId());
                serviceAritcle.setName(serviceList.getName());
                PageHelper.startPage(currentPage,pageSize);
                PageInfo<Article> articlePageInfo = new PageInfo<>(articleRepository.findAllbyOpen(serviceList.getId(),"OPEN"));
                List<Article> articleList = articlePageInfo.getList();
                List<Article> articleListsize = articleRepository.findAllbyOpenNolimit(serviceList.getId(),"OPEN");
                serviceAritcle.setSize(articleListsize.size());
                serviceAritcle.setPageNum(articlePageInfo.getPages());
                serviceAritcle.setPageInfo(articlePageInfo);
                if (articleList.size() != 0){
                    List<AddArticleData> addArticleData = new ArrayList<>();
                    for (Article addArticleDatum : articleList) {
                        AddArticleData addArticle = new AddArticleData();
                        addArticle.setId(addArticleDatum.getId());
                        addArticle.setArticleContent(addArticleDatum.getArticleContent());
                        addArticle.setArticleName(addArticleDatum.getArticleName());
                        addArticle.setAuthorName(addArticleDatum.getAuthorName());
                        ZoneId zoneId = ZoneId.systemDefault();
                        ZonedDateTime zdt = addArticleDatum.getCreateTime().atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
                        Date date = Date.from(zdt.toInstant());
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                        String time = dateformat.format(date);
                        addArticle.setCreateTime(time);
                        addArticle.setServiceId(addArticleDatum.getServiceId());
                        addArticleData.add(addArticle);
                    }
                    serviceAritcle.setArticleList(addArticleData);
                    serviceAritcleList.add(serviceAritcle);
                }
            }
        }
        return serviceAritcleList;
    }
}
