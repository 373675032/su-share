package com.share.mapper;

import com.share.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
 * @InterfaceName MessageMapper
 * @Description (Message)表数据库访问层
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Mapper
public interface MessageMapper {

    /**
     * @Description 添加Message
     * @param message 实例对象
     * @return 影响行数
     */
    int insert(Message message);
    
    /**
     * @Description 删除Message
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @Description 删除Message
     * @param uId 用户ID
     * @return 影响行数
     */
    int deleteByUserId(Integer uId);

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
     * @Description 修改Message,根据 message 的主键修改数据
     * @param message
     * @return 影响行数
     */
    int update(Message message);

     /**
      * @Description 将用户全部消息设为已读
      * @param userId
      * @return 影响行数
      */
    int updateAllRead(Integer userId);

}