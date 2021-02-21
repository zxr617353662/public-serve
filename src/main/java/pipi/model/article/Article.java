package pipi.model.article;

import lombok.Data;
import pipi.model.TableNames;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = TableNames.ARTICLE)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long serviceId;
    private Long hot;
    private String articleName;
    private String articleContent;
    private String authorName;
    private String state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
