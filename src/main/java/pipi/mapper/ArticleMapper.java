package pipi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pipi.model.TableNames;
import pipi.model.article.AddArticleData;
import pipi.model.article.Article;
import pipi.model.article.ArticleFile;
import pipi.model.service.ServiceList;
import pipi.mybatis.BaseMapper;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {


    @Select(
            "select * from "+ TableNames.ARTICLE+" "+"where service_id = #{serId} order by create_time desc"
    )
    List<Article> findAllBySerId(Long serId);
    @Select(
            "select * from "+ TableNames.ARTICLE+" "+"where service_id = #{serId} and state = #{open}  order by create_time desc"
    )
    List<Article> findAllbyOpen(@Param("serId") Long serId, @Param("open") String open);

    @Select(
            "select * from "+ TableNames.ARTICLE+" "+"where service_id = #{serId} and state = #{open}  order by create_time desc"
    )
    List<Article> findAllbyOpenNolimit(@Param("serId") Long serId, @Param("open") String open);


    @Select(
            "select * from "+ TableNames.ARTICLE+" "+"where state =#{open} order by hot desc,id desc"
    )
    List<Article> findByhot(String open);

    @Select(
            "select * from "+ TableNames.ARTICLE+" "+"where state =#{open} order by id desc limit 5"
    )
    List<Article> findAllOrderById(String open);

    @Select(
            "select count(*) from "+ TableNames.ARTICLE+" "+"where state =#{open} and service_id= #{serId} "
    )
    Integer findNumBySerId(@Param("serId") Long id, @Param("open") String open);
}
