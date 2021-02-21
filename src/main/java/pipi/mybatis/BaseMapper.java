package pipi.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 2019-07-02 created by zado
 *
 * include:
 *   1.Mapper<T> interface
 *   [select]
 *   List<T> select(T record)
 *   T       selectByPrimaryKey(Object key)
 *   List<T> selectAll()
 *   int     selectCount(T record)
 *
 *   [insert]
 *   int insert(T record)
 *   int insertSelective(T record)
 *
 *   [update]
 *   int updateByPrimaryKey(T record)
 *   int updateByPrimaryKeySelective(T record)
 *
 *   [delete]
 *   int delete(T record)
 *   int deleteByPrimaryKey(Object key)
 *
 *   [example]
 *   List<T> selectByExample(Object example)
 *   int     selectCountByExample(Object example)
 *   int     updateByExample(@Param("record") T record, @Param("example) Object example)
 *   int     deleteByExample(Object example)
 *
 *   [RowBounds]
 *   List<T> selectByRowBounds(T record, RowBounds rowBounds)
 *   List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds)
 *   List<T> selectByConditionAndRowBounds(Object condition, RowBounds rowBounds)
 *
 *   2.MySql<T>  interface
 *   int insertList(List<T> recordList)
 *   int insertUseGeneratedKeys(T record)
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
