package pipi.service;


import pipi.model.file.FileManagement;
import pipi.model.file.FileManagementData;
import pipi.model.file.FileManagementUserData;
import pipi.model.login.Login;
import pipi.model.login.Role;
import pipi.model.message.MessageList;

import java.util.List;

public interface FileService {


    void addfile(FileManagementData fileManagementData);

    List<FileManagementUserData> fileList();

    FileManagement findNewestOne();

    void save(FileManagement fileManagement1);

    void update(Long id);
}
