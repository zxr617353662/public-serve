package pipi.model.file;

import lombok.Data;
import pipi.model.User;
import pipi.model.login.Login;

import java.time.LocalDateTime;

@Data
public class FileManagementUserData {
    private Long id;
    private Long userId;
    private String fileName;
    private String state;
    private String filePath;
    private LocalDateTime createTime;
    private Login user;
    private String time;

}
