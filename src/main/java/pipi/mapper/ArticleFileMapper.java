package pipi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pipi.model.TableNames;
import pipi.model.article.Article;
import pipi.model.article.ArticleFile;
import pipi.mybatis.BaseMapper;

import java.util.List;

public interface ArticleFileMapper extends BaseMapper<ArticleFile> {


    @Select(
            "select * from "+ TableNames.ARTICLE+" "+"where service_id = #{serId} order by create_time desc"
    )
    List<Article> findAllBySerId(Long serId);
    @Select(
            "select * from "+ TableNames.ARTICLE+" "+"where service_id = #{serId} and state = #{open}  order by create_time desc limit 5"
    )
    List<Article> findAllbyOpen(@Param("serId") Long serId, @Param("open") String open);

    @Select(
            "select * from "+ TableNames.ARTICLE+" "+"where service_id = #{serId} and state = #{open}  order by create_time desc"
    )
    List<Article> findAllbyOpenNolimit(@Param("serId") Long serId, @Param("open") String open);


    @Select(
            "select * from "+TableNames.ARTICLE_FILE+" "+"where art_id = #{id} "
    )
    ArticleFile findByArtId(Long id);

    @Select(
            "select * from "+TableNames.ARTICLE_FILE+" "+"order by create_time desc limit 1 "
    )
    ArticleFile findAllDiff();
}
