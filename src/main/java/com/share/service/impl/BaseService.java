package com.share.service.impl;

import com.share.mapper.*;

import javax.annotation.Resource;

/**
 * @ClassName: BaseService
 * @Description: TODO
 * @author: 莫提
 * @date 2020/11/2 20:53
 * @Version: 1.0
 */
public class BaseService {

    @Resource
    protected CommentMapper commentMapper;

    @Resource
    protected FavoriteMapper favoriteMapper;

    @Resource
    protected KindMapper kindMapper;

    @Resource
    protected LinkMapper linkMapper;

    @Resource
    protected MaterialMapper materialMapper;

    @Resource
    protected MessageMapper messageMapper;

    @Resource
    protected NoticeMapper noticeMapper;

    @Resource
    protected UserMapper userMapper;
}
