package pipi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pipi.mapper.LoginMapper;
import pipi.model.login.Login;
import pipi.model.login.Role;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class LoginRepository {
    @Autowired
    private LoginMapper loginMapper;


    public Login findByNameAndPassword(String name, String password, Role role) {
        return loginMapper.findByNameAndPassword(name,password,role);
    }

    public List<Login> findAll() {
        return loginMapper.selectAll();
    }

    public Integer findCountUser(String user) {
        return loginMapper.findCountUser(user);
    }

    public List<Login> findByName(String name) {
        return loginMapper.findByName("%"+name+"%");
    }

    public void add(Login login) {
        loginMapper.insert(login);
    }

    public List<Login> findAllByRole(String user) {
        return loginMapper.findAllByRole(user);
    }

    public Login findById(Long userId) {
        return loginMapper.selectByPrimaryKey(userId);
    }
}
