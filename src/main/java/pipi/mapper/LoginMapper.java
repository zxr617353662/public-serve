package pipi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pipi.model.TableNames;
import pipi.model.login.Login;
import pipi.model.login.Role;
import pipi.mybatis.BaseMapper;

import java.util.List;

public interface LoginMapper extends BaseMapper<Login> {


    @Select(
            "select * from "+ TableNames.LOGIN+" "+"where name = #{name } and password = #{password} and role = #{role}"
    )
    Login findByNameAndPassword(@Param("name") String name, @Param("password") String password,@Param("role") Role role);

    @Select(
            "select count(*) from "+TableNames.LOGIN +" "+"where role = #{user} "
    )
    Integer findCountUser(String user);

    @Select(
            "select * from "+TableNames.LOGIN+" "+"where name like #{name}"

    )
    List<Login> findByName(String name);

    @Select(
            "select * from "+TableNames.LOGIN+" "+"where role = #{user}"
    )
    List<Login> findAllByRole(String user);
}
