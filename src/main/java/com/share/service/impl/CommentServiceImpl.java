package com.share.service.impl;

import com.share.entity.Comment;
import com.share.mapper.CommentMapper;
import com.share.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

 /**
 * @ClassName CommentServiceImpl
 * @Description (Comment)表业务接口实现类
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Service("commentService")
public class CommentServiceImpl extends BaseService implements CommentService {

    @Override
    public boolean insert(Comment comment) {
        if(commentMapper.insert(comment) == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        if(commentMapper.deleteById(id) == 1){

            return true;
        }
        return false;
    }

     @Override
     public int deleteByReplyId(Integer id) {
         return commentMapper.deleteByReplyId(id);
     }

     @Override
     public int deleteByMaterialId(Integer id) {
         return commentMapper.deleteByMaterialId(id);
     }

     @Override
     public Integer getStarAvg(Integer id) {
         return commentMapper.getStarAvg(id);
     }

     @Override
    public Comment getById(Integer id) {
        return commentMapper.getById(id);
    }

    @Override
    public List<Comment> listComments() {
        return commentMapper.listComments();
    }

    @Override
    public List<Comment> listComments(Comment comment) {
        return commentMapper.listComments(comment);
    }

     @Override
     public List<Comment> listFirstLevel(Integer mId) {
         return commentMapper.listFirstLevel(mId);
     }

     @Override
     public List<Comment> listSecondLevel(Integer id) {
         return commentMapper.listSecondLevel(id);
     }

     @Override
    public boolean update(Comment comment) {
        if(commentMapper.update(comment) == 1){
            return true;
        }
        return false;
    }

}