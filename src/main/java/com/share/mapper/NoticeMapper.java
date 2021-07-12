package com.share.mapper;

import com.share.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
 * @InterfaceName NoticeMapper
 * @Description (Notice)表数据库访问层
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Mapper
public interface NoticeMapper {

    /**
     * @Description 添加Notice
     * @param notice 实例对象
     * @return 影响行数
     */
    int insert(Notice notice);
    
    /**
     * @Description 删除Notice
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

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
     * @Description 修改Notice,根据 notice 的主键修改数据
     * @param notice
     * @return 影响行数
     */
    int update(Notice notice);

}