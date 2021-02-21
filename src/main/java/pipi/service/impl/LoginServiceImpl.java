package pipi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pipi.model.login.Login;
import pipi.model.login.Role;
import pipi.repository.LoginRepository;
import pipi.service.LoginService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository loginRepository;
    @Override
    public Login login(String name, String password, Role role) {
        Login login = loginRepository.findByNameAndPassword(name,password,role);
        if (login != null){
            return login;
        }else{
            return null;
        }
    }

    @Override
    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Integer findCountUser() {
        return loginRepository.findCountUser("USER");
    }

    @Override
    public List<Login> findByName(String name) {
        return loginRepository.findByName(name);
    }

    @Override
    public void add(Login login) throws Exception {
        List<Login> loginList = loginRepository.findAllByRole("USER");
        for (Login login1 : loginList) {
            if (login.getName().equals(login1.getName())){
                throw new Exception("用户名已存在");
            }
        }
        login.setCreateTime(LocalDateTime.now());
        login.setRole("USER");
        loginRepository.add(login);
    }
}
