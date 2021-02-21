package pipi.service;

import pipi.model.service.ServiceAritcle;
import pipi.model.service.ServiceList;

import java.util.List;

public interface SerService {
    void addserviceName(String name);

    List<ServiceList> serviceList();

    void findById(Long id,String state);

    void delSer(Long id);

    ServiceList findByIdNoSt(Long serId);

    List<ServiceList> serviceListLi();

    List<ServiceAritcle> findAllArticle(Integer currentPage,Integer pageSize);
    List<ServiceAritcle> findAllArticle1(Integer currentPage,Integer pageSize);

}
