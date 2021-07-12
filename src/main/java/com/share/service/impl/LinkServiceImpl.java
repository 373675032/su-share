package com.share.service.impl;

import com.share.entity.Link;
import com.share.mapper.LinkMapper;
import com.share.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

 /**
 * @ClassName LinkServiceImpl
 * @Description (Link)表业务接口实现类
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Service("linkService")
public class LinkServiceImpl extends BaseService implements LinkService {

    /**
     * @Description 添加Link
     * @param link 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Link link) {
        if(linkMapper.insert(link) == 1){
            return true;
        }
        return false;
    }

    /**
     * @Description 删除Link
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        if(linkMapper.deleteById(id) == 1){
            return true;
        }
        return false;
    }

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Link getById(Integer id) {
        return linkMapper.getById(id);
    }

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    @Override
    public List<Link> listLinks() {
        return linkMapper.listLinks();
    }

    /**
     * @Description 实体作为筛选条件查询数据
     * @param link 实例对象
     * @return 对象列表
     */
    @Override
    public List<Link> listLinks(Link link) {
        return linkMapper.listLinks(link);
    }

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param link 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Link link) {
        if(linkMapper.update(link) == 1){
            return true;
        }
        return false;
    }

}