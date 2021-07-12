package com.share.service;

import com.share.entity.Kind;
import java.util.List;

/**
 * @InterfaceName KindService
 * @Description (Kind)表业务接口
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
public interface KindService {

    /**
     * @Description 添加Kind
     * @param kind 实例对象
     * @return 是否成功
     */
    boolean insert(Kind kind);

    /**
     * @Description 删除Kind
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Kind getById(Integer id);

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Kind> listKinds();

    /**
     * @Description 实体作为筛选条件查询数据
     * @param kind 实例对象
     * @return 对象列表
     */
    List<Kind> listKinds(Kind kind);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param kind 实例对象
     * @return 是否成功
     */
    boolean update(Kind kind);

}