package com.share.service.impl;

import com.share.entity.Kind;
import com.share.mapper.KindMapper;
import com.share.service.KindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

 /**
 * @ClassName KindServiceImpl
 * @Description (Kind)表业务接口实现类
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Service("kindService")
public class KindServiceImpl extends BaseService implements KindService {

    /**
     * @Description 添加Kind
     * @param kind 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Kind kind) {
        if(kindMapper.insert(kind) == 1){
            return true;
        }
        return false;
    }

    /**
     * @Description 删除Kind
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        if(kindMapper.deleteById(id) == 1){
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
    public Kind getById(Integer id) {
        return kindMapper.getById(id);
    }

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    @Override
    public List<Kind> listKinds() {
        return kindMapper.listKinds();
    }

    /**
     * @Description 实体作为筛选条件查询数据
     * @param kind 实例对象
     * @return 对象列表
     */
    @Override
    public List<Kind> listKinds(Kind kind) {
        return kindMapper.listKinds(kind);
    }

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param kind 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Kind kind) {
        if(kindMapper.update(kind) == 1){
            return true;
        }
        return false;
    }

}