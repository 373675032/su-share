package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.share.entity.Favorite;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: FavoriteController
 * @Description: TODO
 * @author: 莫提
 * @date 2020/11/8 15:46
 * @Version: 1.0
 */
@Controller
public class FavoriteController extends BaseController {
    
    /**
     * 添加到收藏夹、取消收藏
     */
    @PostMapping("/updateFavorite")
    @ResponseBody
    public String updateFavorite(@RequestParam("mId")Integer mId){
        Favorite favorite = Favorite.builder().materialId(mId).userId(loginUser.getId()).build();
        List<Favorite> list = favoriteService.listFavorites(favorite);
        // 已经收藏过
        if (list.size() > 0) {
            // 取消收藏
            favoriteService.deleteById(list.get(0).getId());
            result.setCode(201);
        }else {
            // 添加收藏
            if (favoriteService.insert(favorite)) {
                result.setCode(200);
            }else {
                result.setCode(500);
            }
        }
        return JSONObject.toJSONString(result);
    }
}
