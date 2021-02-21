package pipi.service;


import pipi.model.login.Login;
import pipi.model.login.Role;

import java.util.List;

public interface LoginService {

    Login login(String name, String password, Role role);

    List<Login> findAll();

    Integer findCountUser();

    List<Login> findByName(String name);

    void add(Login login) throws Exception;
}
