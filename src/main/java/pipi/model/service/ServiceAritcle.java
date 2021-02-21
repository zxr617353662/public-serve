package pipi.model.service;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import pipi.model.article.AddArticleData;

import java.util.List;

@Data
public class ServiceAritcle {
    private Long id;
    private String name;
    private List<AddArticleData> articleList;
    private Integer size;
    private Integer pageNum;
    private PageInfo pageInfo;

}
