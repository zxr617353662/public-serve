package pipi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pipi.model.TableNames;
import pipi.model.login.Login;
import pipi.model.login.Role;
import pipi.model.service.ServiceList;
import pipi.model.service.ServiceState;
import pipi.mybatis.BaseMapper;

import java.util.List;

public interface SerMapper extends BaseMapper<ServiceList> {


    @Select(
            "select * from "+TableNames.SERVICE_LIST +" "+"order by id desc"
    )
    List<ServiceList> selectAllasc();

    @Select(
            "select * from "+TableNames.SERVICE_LIST +" "+"where id != 1 order by id desc"
    )
    List<ServiceList> serviceListLi();

    @Select(
            "select * from "+TableNames.SERVICE_LIST +" "+"where state = #{open} order by id desc limit 4"
    )
    List<ServiceList> findAllOpen(@Param("open") ServiceState open);


    @Select(
            "select * from "+TableNames.SERVICE_LIST +" "+"where state = #{open} order by id desc"
    )
    List<ServiceList> findAllOpen1(@Param("open") ServiceState open);



}
