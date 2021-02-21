package pipi.model.file;

import lombok.Data;
import pipi.model.TableNames;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = TableNames.FILE_MANAGEMENT)
public class FileManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String fileName;
    private String state;
    private String filePath;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
