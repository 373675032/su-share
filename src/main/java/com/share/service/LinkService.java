package com.share.service;

import com.share.entity.Link;
import java.util.List;

/**
 * @InterfaceName LinkService
 * @Description (Link)表业务接口
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
public interface LinkService {

    /**
     * @Description 添加Link
     * @param link 实例对象
     * @return 是否成功
     */
    boolean insert(Link link);

    /**
     * @Description 删除Link
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

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
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param link 实例对象
     * @return 是否成功
     */
    boolean update(Link link);

}