package com.share.mapper;

import com.share.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
 * @InterfaceName LinkMapper
 * @Description (Link)表数据库访问层
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Mapper
public interface LinkMapper {

    /**
     * @Description 添加Link
     * @param link 实例对象
     * @return 影响行数
     */
    int insert(Link link);
    
    /**
     * @Description 删除Link
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Link getById(Integer id);

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Link> listLinks();

    /**
     * @Description 实体作为筛选条件查询数据
     * @param link 实例对象
     * @return 对象列表
     */
    List<Link> listLinks(Link link);

    /**
     * @Description 修改Link,根据 link 的主键修改数据
     * @param link
     * @return 影响行数
     */
    int update(Link link);

}