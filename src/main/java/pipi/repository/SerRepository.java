package pipi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pipi.mapper.LoginMapper;
import pipi.mapper.SerMapper;
import pipi.model.login.Login;
import pipi.model.login.Role;
import pipi.model.service.ServiceList;
import pipi.model.service.ServiceState;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class SerRepository {
    @Autowired
    private SerMapper serMapper;

    public void addserviceName(ServiceList serviceList) {
        serMapper.insert(serviceList);
    }

    public List<ServiceList> serviceList() {
        return serMapper.selectAllasc();
    }

    public ServiceList findById(Long id) {
        return serMapper.selectByPrimaryKey(id);
    }

    public void updateSer(ServiceList byId) {
        serMapper.updateByPrimaryKeySelective(byId);
    }

    public void delSer(Long id) {
        serMapper.deleteByPrimaryKey(id);
    }

    public ServiceList findByIdNoSt(Long serId) {
        return serMapper.selectByPrimaryKey(serId);
    }

    public List<ServiceList> serviceListLi() {
        return serMapper.serviceListLi();
    }

    public List<ServiceList> findAllOpen(ServiceState open) {
        return serMapper.findAllOpen(open);
    }
    public List<ServiceList> findAllOpen1(ServiceState open) {
        return serMapper.findAllOpen1(open);
    }
}
