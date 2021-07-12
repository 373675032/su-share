package com.share.service.impl;

import com.share.entity.Notice;
import com.share.mapper.NoticeMapper;
import com.share.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

 /**
 * @ClassName NoticeServiceImpl
 * @Description (Notice)表业务接口实现类
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Service("noticeService")
public class NoticeServiceImpl extends BaseService implements NoticeService {

    /**
     * @Description 添加Notice
     * @param notice 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Notice notice) {
        if(noticeMapper.insert(notice) == 1){
            return true;
        }
        return false;
    }

    /**
     * @Description 删除Notice
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        if(noticeMapper.deleteById(id) == 1){
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
    public Notice getById(Integer id) {
        return noticeMapper.getById(id);
    }

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    @Override
    public List<Notice> listNotices() {
        return noticeMapper.listNotices();
    }

    /**
     * @Description 实体作为筛选条件查询数据
     * @param notice 实例对象
     * @return 对象列表
     */
    @Override
    public List<Notice> listNotices(Notice notice) {
        return noticeMapper.listNotices(notice);
    }

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param notice 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Notice notice) {
        if(noticeMapper.update(notice) == 1){
            return true;
        }
        return false;
    }

}