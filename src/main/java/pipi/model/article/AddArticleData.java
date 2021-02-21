package pipi.model.article;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddArticleData implements Serializable {
    private Long id;
    private Long serviceId;
    private String articleName;
    private String articleContent;
    private String authorName;
    private String createTime;

}
