package com.share.service.impl;

import com.share.entity.Favorite;
import com.share.entity.Material;
import com.share.mapper.FavoriteMapper;
import com.share.service.FavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

 /**
 * @ClassName FavoriteServiceImpl
 * @Description (Favorite)表业务接口实现类
 * @author 莫提
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Service("favoriteService")
public class FavoriteServiceImpl extends BaseService implements FavoriteService {

    @Override
    public boolean insert(Favorite favorite) {
        if(favoriteMapper.insert(favorite) == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        if(favoriteMapper.deleteById(id) == 1){
            return true;
        }
        return false;
    }

    @Override
    public Favorite getById(Integer id) {
        return favoriteMapper.getById(id);
    }

    @Override
    public List<Favorite> listFavorites() {
        return favoriteMapper.listFavorites();
    }

    @Override
    public List<Favorite> listFavorites(Favorite favorite) {
        return favoriteMapper.listFavorites(favorite);
    }

    @Override
    public boolean update(Favorite favorite) {
        if(favoriteMapper.update(favorite) == 1){
            return true;
        }
        return false;
    }

     @Override
     public List<Material> listFavoriteMaterial(Integer uId) {
         return favoriteMapper.listFavoriteMaterial(uId);
     }

 }