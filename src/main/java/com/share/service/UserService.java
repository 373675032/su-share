package com.share.service;

import com.share.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @InterfaceName UserService
 * @Description (User)表业务接口
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
public interface UserService {

    /**
     * @Description 添加User
     * @param user 实例对象
     * @return 是否成功
     */
    boolean insert(User user);

    /**
     * @Description 删除User
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    User getById(Integer id);

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<User> listUsers();

    /**
     * @Description 实体作为筛选条件查询数据
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> listUsers(User user);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param user 实例对象
     * @return 是否成功
     */
    boolean update(User user);

    /**
     * @Description 查看当前多少注册用户
     * @return 查询结果
     */
    Integer countUser();

}