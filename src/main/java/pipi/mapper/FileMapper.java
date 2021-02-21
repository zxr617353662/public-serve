package pipi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pipi.model.TableNames;
import pipi.model.file.FileManagement;
import pipi.model.file.FileManagementUserData;
import pipi.model.message.MessageList;
import pipi.model.notice.Notice;
import pipi.mybatis.BaseMapper;

import java.util.List;

public interface FileMapper extends BaseMapper<FileManagement> {


    @Select(
            "select * from "+TableNames.MESSAGE_LIST +" "+"where into_id = #{id} order by update_time desc"
    )
    List<MessageList> findMessageListById(Long id);

    @Select(
            "select * from "+TableNames.FILE_MANAGEMENT +" "+"order by create_time desc limit 1"
    )
    FileManagement findNewestOne();

    @Select(
            "select * from "+TableNames.FILE_MANAGEMENT +" "+"order by state desc"
    )
    List<FileManagementUserData> fileList();
}
