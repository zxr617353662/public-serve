package pipi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pipi.model.TableNames;
import pipi.model.notice.Notice;
import pipi.model.service.ServiceList;
import pipi.mybatis.BaseMapper;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice> {


    @Select(
            "select * from "+TableNames.NOTICE+" "+"order by id desc"
    )
    List<Notice> listNotice();

    @Select(
            "select * from "+TableNames.NOTICE+" "+"where notice_title like #{title}"
    )
    List<Notice> listNoticeFindByTitle(@Param("title") String title);
}
