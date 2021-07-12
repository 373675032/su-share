package com.share.service.impl;

import com.share.entity.User;
import com.share.mapper.UserMapper;
import com.share.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

 /**
 * @ClassName UserServiceImpl
 * @Description (User)表业务接口实现类
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public boolean insert(User user) {
        if(userMapper.insert(user) == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        if(userMapper.deleteById(id) == 1){
            return true;
        }
        return false;
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    @Override
    public List<User> listUsers(User user) {
        return userMapper.listUsers(user);
    }

    @Override
    public boolean update(User user) {
        if(userMapper.update(user) == 1){
            return true;
        }
        return false;
    }

     @Override
     public Integer countUser() {
         return userMapper.countUser();
     }

 }