package com.share.mapper;

import com.share.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

 /**
 * @InterfaceName CommentMapper
 * @Description (Comment)表数据库访问层
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Mapper
public interface CommentMapper {

    /**
     * @Description 添加Comment
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);
    
    /**
     * @Description 删除Comment
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

     /**
      * @Description 删除二级评论
      * @param id 主键
      * @return 影响行数
      */
    int deleteByReplyId(Integer id);

     /**
      * @Description 删除素材的所有
      * @param id 主键
      * @return 影响行数
      */
     int deleteByMaterialId(Integer id);

     /**
      * @Description 获取素材的评价评星数
      * @param id 主键
      * @return 星星数
      */
    Integer getStarAvg(Integer id);

    /**
     * @Description 查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Comment getById(Integer id);

    /**
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Comment> listComments();

    /**
     * @Description 实体作为筛选条件查询数据
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> listComments(Comment comment);

     /**
      * @Description 获取指定素材的一级评论
      * @param mId 素材ID
      * @return 对象列表
      */
    List<Comment> listFirstLevel(Integer mId);

     /**
      * @Description 获取指定素材的二级评论
      * @param id 一级评论的ID
      * @return 对象列表
      */
    List<Comment> listSecondLevel(Integer id);

    /**
     * @Description 修改Comment,根据 comment 的主键修改数据
     * @param comment
     * @return 影响行数
     */
    int update(Comment comment);

}