package com.share.service;

import com.share.entity.Material;
import com.share.vo.MaterialVo;

import java.util.List;

/**
 * @InterfaceName MaterialService
 * @Description (Material)表业务接口
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
public interface MaterialService {

    /**
     * @Description 添加Material
     * @param material 实例对象
     * @return 是否成功
     */
    boolean insert(Material material);

    /**
     * @Description 删除Material
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Material getById(Integer id);

    /**
     * @Description 查询单条数据的视图对象
     * @param id 主键
     * @return 实例对象
     */
    Material getVoById(Integer id);

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Material> listMaterials();

    /**
     * @Description 实体作为筛选条件查询数据
     * @param material 实例对象
     * @return 对象列表
     */
    List<Material> listMaterials(Material material);

    /**
     * @Description 素材名称模糊查询
     * @param name 关键字
     * @return 对象列表
     */
    List<Material> listMaterialsByName(String name);

    /**
     * @Description 根据用户ID查询所有素材
     * @param uId 用户ID
     * @return 对象列表
     */
    List<Material> listMaterialsByUserId(Integer uId);

    /**
     * @Description 获取指定类别的素材根据下载量降序
     * @param kindId 分类的ID
     * @return 对象列表
     */
    List<Material> listMaterialsByKindIdorderByDownloadCount(Integer kindId);

    /**
     * @Description 获取指定类别的素材根据下载量降序,排除指定ID
     * @param kindId 分类的ID
     * @return 对象列表
     */
    List<Material> listMaterialsByKindIdorderByDownloadCount(Integer kindId,Integer mId);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param material 实例对象
     * @return 是否成功
     */
    boolean update(Material material);

    /**
     * @Description 获取主页需要展示的所有素材
     * @return 对象列表
     */
    List<Material> listIndexMaterials();

    /**
     * @Description 实体作为筛选条件获得结果数量
     * @param material 实例对象
     * @return 对象列表
     */
    int countMaterials(Material material);

    /**
     * @Description 根据阅读量排序某个类型下的素材
     * @return 对象列表
     */
    List<Material> orderByDownloadCount(Integer kId);
}