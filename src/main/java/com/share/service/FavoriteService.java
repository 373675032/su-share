package com.share.service;

import com.share.entity.Favorite;
import com.share.entity.Material;

import java.util.List;

/**
 * @InterfaceName FavoriteService
 * @Description (Favorite)表业务接口
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
public interface FavoriteService {

    /**
     * @Description 添加Favorite
     * @param favorite 实例对象
     * @return 是否成功
     */
    boolean insert(Favorite favorite);

    /**
     * @Description 删除Favorite
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Favorite getById(Integer id);

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Favorite> listFavorites();

    /**
     * @Description 实体作为筛选条件查询数据
     * @param favorite 实例对象
     * @return 对象列表
     */
    List<Favorite> listFavorites(Favorite favorite);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param favorite 实例对象
     * @return 是否成功
     */
    boolean update(Favorite favorite);

    /**
     * @Description 获取用户收藏的素材
     * @param uId 用户ID
     * @return 对象列表
     */
    List<Material> listFavoriteMaterial(Integer uId);

}