package pipi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pipi.model.ResponseStmt;
import pipi.model.login.Login;
import pipi.service.LoginService;


@Controller
public class UserController {
    @Autowired
    LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "/reg",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStmt listNotice(@RequestBody Login login,
                                   @RequestParam("password") String password) throws Exception {
        if (!login.getPassword().equals(password)){
            throw new Exception("密码不相等");
        }
        loginService.add(login);
        return ResponseStmt.ADD_OK;
    }
}
