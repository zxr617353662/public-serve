package pipi.model.service;

import lombok.Data;
import pipi.model.TableNames;
import pipi.model.login.Role;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = TableNames.SERVICE_LIST)
public class ServiceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
