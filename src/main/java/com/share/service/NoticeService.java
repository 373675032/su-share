package com.share.service;

import com.share.entity.Notice;
import java.util.List;

/**
 * @InterfaceName NoticeService
 * @Description (Notice)表业务接口
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
public interface NoticeService {

    /**
     * @Description 添加Notice
     * @param notice 实例对象
     * @return 是否成功
     */
    boolean insert(Notice notice);

    /**
     * @Description 删除Notice
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Notice getById(Integer id);

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Notice> listNotices();

    /**
     * @Description 实体作为筛选条件查询数据
     * @param notice 实例对象
     * @return 对象列表
     */
    List<Notice> listNotices(Notice notice);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @param notice 实例对象
     * @return 是否成功
     */
    boolean update(Notice notice);

}