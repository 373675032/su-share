package com.share.service.impl;

import com.share.entity.Message;
import com.share.mapper.MessageMapper;
import com.share.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

 /**
 * @ClassName MessageServiceImpl
 * @Description (Message)表业务接口实现类
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Service("messageService")
public class MessageServiceImpl extends BaseService implements MessageService {

    /**
     * @Description 添加Message
     * @param message 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Message message) {
        if(messageMapper.insert(message) == 1){
            return true;
        }
        return false;
    }

    /**
     * @Description 删除Message
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        if(messageMapper.deleteById(id) == 1){
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
    public Message getById(Integer id) {
        return messageMapper.getById(id);
    }

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    @Override
    public List<Message> listMessages() {
        return messageMapper.listMessages();
    }

    /**
     * @Description 实体作为筛选条件查询数据
     * @param message 实例对象
     * @return 对象列表
     */
    @Override
    public List<Message> listMessages(Message message) {
        return messageMapper.listMessages(message);
    }

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param message 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Message message) {
        if(messageMapper.update(message) == 1){
            return true;
        }
        return false;
    }

     @Override
     public boolean updateAllRead(Integer userId) {
         return messageMapper.updateAllRead(userId) > 0;
     }

     @Override
     public int deleteByUserId(Integer uId) {
         return messageMapper.deleteByUserId(uId);
     }

 }