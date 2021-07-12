package com.share.mapper;

import com.share.entity.Kind;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
 * @InterfaceName KindMapper
 * @Description (Kind)表数据库访问层
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Mapper
public interface KindMapper {

    /**
     * @Description 添加Kind
     * @param kind 实例对象
     * @return 影响行数
     */
    int insert(Kind kind);
    
    /**
     * @Description 删除Kind
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

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
     * @Description 修改Kind,根据 kind 的主键修改数据
     * @param kind
     * @return 影响行数
     */
    int update(Kind kind);

}