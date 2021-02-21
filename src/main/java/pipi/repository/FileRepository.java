package pipi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pipi.mapper.FileMapper;
import pipi.model.file.FileManagement;
import pipi.model.file.FileManagementUserData;
import pipi.model.message.MessageList;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class FileRepository {
    @Autowired
    private FileMapper fileMapper;


    public List<MessageList> findMessageListById(Long id) {
        return fileMapper.findMessageListById(id);
    }

    public void addfile(FileManagement fileManagement) {
        fileMapper.insert(fileManagement);
    }

    public List<FileManagementUserData> fileList() {
        return fileMapper.fileList();
    }

    public FileManagement findNewestOne() {
        return fileMapper.findNewestOne();
    }

    public void save(FileManagement fileManagement1) {
        fileMapper.insert(fileManagement1);
    }

    public FileManagement findById(Long id) {
        return fileMapper.selectByPrimaryKey(id);
    }

    public void update(FileManagement fileManagement) {
        fileMapper.updateByPrimaryKeySelective(fileManagement);
    }
}
