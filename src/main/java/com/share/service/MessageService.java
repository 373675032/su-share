package com.share.service;

import com.share.entity.Message;
import java.util.List;

/**
 * @InterfaceName MessageService
 * @Description (Message)表业务接口
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
public interface MessageService {

    /**
     * @Description 添加Message
     * @param message 实例对象
     * @return 是否成功
     */
    boolean insert(Message message);

    /**
     * @Description 删除Message
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Message getById(Integer id);

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Message> listMessages();

    /**
     * @Description 实体作为筛选条件查询数据
     * @param message 实例对象
     * @return 对象列表
     */
    List<Message> listMessages(Message message);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param message 实例对象
     * @return 是否成功
     */
    boolean update(Message message);

    /**
     * @Description 将用户全部消息设为已读
     * @param userId
     * @return 影响行数
     */
    boolean updateAllRead(Integer userId);

    /**
     * @Description 删除Message
     * @param uId 用户ID
     * @return 影响行数
     */
    int deleteByUserId(Integer uId);

}