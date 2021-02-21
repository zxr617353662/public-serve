package pipi.model.article;

import lombok.Data;

@Data
public class PushArticleList {
    private String serName;
    private Long articleId;
    private String articleName;
    private String articleContent;
    private String authorName;
    private String createTime;
}
