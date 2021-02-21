package pipi.service.impl;

import com.mysql.cj.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pipi.model.TimeChangeUtil;
import pipi.model.file.FileManagement;
import pipi.model.file.FileManagementData;
import pipi.model.file.FileManagementUserData;
import pipi.model.login.Login;
import pipi.model.message.MessageList;
import pipi.repository.FileRepository;
import pipi.repository.LoginRepository;
import pipi.service.FileService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    LoginRepository loginRepository;

    @Override
    public void addfile(FileManagementData fileManagementData) {
        FileManagement fileManagement = new FileManagement();
        fileManagement.setFileName(fileManagementData.getFileName());
        fileManagement.setState("DISPOSE");
        fileManagement.setCreateTime(LocalDateTime.now());
        fileRepository.addfile(fileManagement);
    }

    @Override
    public List<FileManagementUserData> fileList() {
        List<FileManagementUserData> fileManagementUserData = fileRepository.fileList();
        if (fileManagementUserData.size() != 0 ){
            for (FileManagementUserData fileManagementUserDatum : fileManagementUserData) {
                fileManagementUserDatum.setTime(TimeChangeUtil.changeTime(fileManagementUserDatum.getCreateTime()));
                Login login = loginRepository.findById(fileManagementUserDatum.getUserId());
                if (login != null) {
                    fileManagementUserDatum.setUser(login);
                }
            }
        }
        return fileManagementUserData;
    }

    @Override
    public FileManagement findNewestOne() {
        return fileRepository.findNewestOne();
    }

    @Override
    public void save(FileManagement fileManagement1) {
        fileRepository.save(fileManagement1);
    }

    @Override
    public void update(Long id) {
        FileManagement fileManagement = fileRepository.findById(id);
        fileManagement.setState("processed");
        fileManagement.setUpdateTime(LocalDateTime.now());
        fileRepository.update(fileManagement);
    }
}
