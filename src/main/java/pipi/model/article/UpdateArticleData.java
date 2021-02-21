package pipi.model.article;

import lombok.Data;

@Data
public class UpdateArticleData {
    private Long id;
    private String articleName;
    private String articleContent;
    private String authorName;
}
