package com.share.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.constant.CheckedStatusType;
import com.share.constant.TrashStatusType;
import com.share.entity.*;
import com.share.utils.LogUtils;
import com.share.vo.CommentVo;
import com.share.vo.MaterialVo;
import com.share.vo.MessageVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SystemController
 * @Description: 系统页面跳转控制器
 * @author: 莫提
 * @date 2020/11/2 20:38
 * @Version: 1.0
 */
@Controller
public class SystemController extends BaseController {

    /**
     * 前往 前台主页
     */
    @GetMapping({"/index","/"})
    public String index(Map<String,Object> map,Integer page){
        // 处理page
        page = ObjectUtils.isEmpty(page)?1:page;
        List<Material> materials = null;
        // 分页查找
        PageHelper.startPage(page,12);
        materials = materialService.listIndexMaterials();

        PageInfo<Material> pageInfo = new PageInfo<>(materials);
        // 获取公告
        Notice notice = noticeService.getById(1);

        map.put("page",page);
        map.put("notice",notice);
        map.put("totalPage",pageInfo.getPages());
        map.put("materialVos",convertMaterialVo(materials));
        return "index";
    }

    /**
     * 前往用户主页
     */
    @GetMapping("/home")
    public String home(Integer id,Map<String,Object> map){
        List<Material> materials = null;
        List<Material> favoriteMaterial = null;
        User user = null;
        if (ObjectUtils.isEmpty(id)){

        }else {
            user = userService.getById(id);
            if (ObjectUtils.isEmpty(user)){
                return "redirect:/index";
            }
            // 获取全部已发表通过审核的素材
            Material build = Material.builder().userId(id).isChecked(CheckedStatusType.PASS).status(TrashStatusType.NOT_IN).build();
            materials = materialService.listMaterials(build);
            // 获取全部收藏
            favoriteMaterial = favoriteService.listFavoriteMaterial(id);
        }
        map.put("materialVos",convertMaterialVo(materials));
        map.put("favoriteMaterialVos",convertMaterialVo(favoriteMaterial));
        map.put("user",user);
        return "home";
    }


    /**
     * 前往 分类素材页面
     */
    @GetMapping("/materials")
    public String materials(Integer kId,Map<String,Object> map,String search){
        // 处理ID
        kId = ObjectUtils.isEmpty(kId) ? 0 : kId;
        kId = StringUtils.isEmpty(search) ? kId : -1;
        List<Material> materials = null;
        List<Material> highDownloadMaterials = null;
        // 获取最多下载的5个素材
        PageHelper.startPage(0,5);
        highDownloadMaterials = materialService.orderByDownloadCount(null);
        if (kId == 0){
            // 查找全部素材
            Material build = Material.builder()
                    .isChecked(CheckedStatusType.PASS).status(TrashStatusType.NOT_IN)
                    .build();
            materials = materialService.listMaterials(build);
        }else if (kId == -1){
            // 根据关键字查询（根据素材名字模糊查询）
            materials = materialService.listMaterialsByName(search);
            map.put("search",search);
        }else {
            PageHelper.startPage(0,5);
            highDownloadMaterials = materialService.orderByDownloadCount(kId);
            // 查找指定分类的素材
            Material build = Material.builder()
                    .isChecked(CheckedStatusType.PASS).status(TrashStatusType.NOT_IN).kindId(kId)
                    .build();
            materials = materialService.listMaterials(build);
            Kind kind = kindService.getById(kId);
            map.put("kind",kind);
        }
        map.put("highDownloadMaterials",highDownloadMaterials);
        map.put("kId",kId);
        map.put("materialVos",convertMaterialVo(materials));
        return "materials";
    }

    /**
     * 前往 素材详情页面
     */
    @GetMapping("/material")
    public String material(@RequestParam("id") Integer id, Map<String,Object> map){
        // 获取素材对象
        Material material = materialService.getVoById(id);
        // 更新阅读量
        material.setReadCount(material.getReadCount() + 1);
        materialService.update(material);
        // 获取同类别下优质素材
        PageHelper.startPage(0,3);
        List<Material> materials = materialService.listMaterialsByKindIdorderByDownloadCount(material.getKindId(),material.getId());
        // 转化为Vo对象
        MaterialVo materialVo = convertMaterialVo(material);
        // 判断是否收藏
        if (loginUser != null){
            List<Favorite> list = favoriteService.listFavorites(Favorite.builder().userId(loginUser.getId()).materialId(id).build());
            if (list.size() > 0){
                materialVo.setFavorite(true);
            }
        }
        // 获取全部评论
        List<CommentVo> commentVos = dealFirstLevelComment(commentService.listFirstLevel(id));
        
        map.put("materialVo",materialVo);
        map.put("commentVos",commentVos);
        map.put("materialVos",convertMaterialVo(materials));
        return "material";
    }

    /**
     * 前往 素材预览页面
     */
    @GetMapping("/preview")
    public String preview(@RequestParam("id") Integer id, Map<String,Object> map){
        // 获取素材对象
        Material material = materialService.getVoById(id);
        // 转化为Vo对象
        MaterialVo materialVo = convertMaterialVo(material);
        map.put("materialVo",materialVo);
        return "preview";
    }

    /**
     * 前往 我的收藏夹
     */
    @GetMapping("/favorites")
    public String favorites(Map<String,Object> map,Integer page){
        // 处理page
        page = ObjectUtils.isEmpty(page)?1:page;
        // 分页查找
        PageHelper.startPage(page,10);
        List<Material> favoriteMaterials = favoriteService.listFavoriteMaterial(loginUser.getId());
        PageInfo<Material> pageInfo = new PageInfo<>(favoriteMaterials);

        map.put("page",page);
        map.put("totalPage",pageInfo.getPages());
        map.put("favoriteMaterials",favoriteMaterials);
        return "favorites";
    }

    /**
     * 前往 上传新素材页面
     */
    @GetMapping("/new")
    public String newMaterial(Map<String,Object> map){
        map.put("kinds",kinds);
        return "upload";
    }
    
    /**
     * 前往 我的资料页面
     */
    @GetMapping("/profile")
    public String profile(Map<String,Object> map){
        map.put("kinds",kinds);
        return "profile";
    }

    /**
     * 前往 我的素材
     */
    @GetMapping("/my-material")
    public String myMaterial(Map<String,Object> map,Integer page){
        // 处理page
        page = ObjectUtils.isEmpty(page)?1:page;
        // 分页查找
        PageHelper.startPage(page,10);
        // 获取我的全部已发布已通过审核的素材
        List<Material> materials = materialService.listMaterialsByUserId(loginUser.getId());
        PageInfo<Material> pageInfo = new PageInfo<>(materials);
        List<MaterialVo> materialVos = convertMaterialVo(materials);

        map.put("page",page);
        map.put("totalPage",pageInfo.getPages());
        map.put("materialVos",materialVos);
        return "my-material";
    }

    /**
     * 前往 回收站
     */
    @GetMapping("/trash")
    public String trash(Map<String,Object> map,Integer page){
        // 处理page
        page = ObjectUtils.isEmpty(page)?1:page;
        Material material = Material.builder().userId(loginUser.getId()).status(TrashStatusType.IS_IN).build();
        // 分页查找
        PageHelper.startPage(page,10);
        // 获取我的全部回收站素材
        List<Material> materials = materialService.listMaterials(material);
        PageInfo<Material> pageInfo = new PageInfo<>(materials);
        List<MaterialVo> materialVos = convertMaterialVo(materials);

        map.put("page",page);
        map.put("totalPage",pageInfo.getPages());
        map.put("materialVos",materialVos);
        return "trash";
    }

    /**
     * 前往 审核队列
     */
    @GetMapping("/audit")
    public String audit(Map<String,Object> map,Integer page,Integer type){
        // 处理page和type
        page = ObjectUtils.isEmpty(page)?1:page;
        type = ObjectUtils.isEmpty(type)?0:type;
        Integer role = loginUser.getRole();
        Integer userId = role == 1 ? null : loginUser.getId();
        // 分页查找
        PageHelper.startPage(page,10);
        // 获取所有待审核的素材
        Material material = Material.builder().userId(userId).isChecked(type).status(TrashStatusType.NOT_IN).build();
        List<Material> materials = materialService.listMaterials(material);
        PageInfo<Material> pageInfo = new PageInfo<>(materials);
        List<MaterialVo> materialVos = convertMaterialVo(materials);

        map.put("page",page);
        map.put("type",type);
        map.put("totalPage",pageInfo.getPages());
        map.put("materialVos",materialVos);
        map.put("role",role);
        return "audit";
    }
    
    /**
     * 前往 消息管理
     */
    @GetMapping("/messages")
    public String messages(Map<String,Object> map,Integer page,Integer type){
        // 处理page和type
        page = ObjectUtils.isEmpty(page)?1:page;
        type = ObjectUtils.isEmpty(type)?0:type;
        // 分页查找
        PageHelper.startPage(page,10);
        // 获取全部未读消息
        Message message = Message.builder().receiveUserId(loginUser.getId()).isRead(type).build();
        List<Message> messages = messageService.listMessages(message);
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        List<MessageVo> messageVos = convertMessageVo(messages);

        LogUtils.log("获得全部消息",messageVos);

        map.put("page",page);
        map.put("type",type);
        map.put("totalPage",pageInfo.getPages());
        map.put("messageVos",messageVos);
        return "messages";
    }

    private List<MaterialVo> convertMaterialVo(List<Material> materials){
        List<MaterialVo> materialVos = new ArrayList<>();
        for (Material material : materials) {
            User user = userService.getById(material.getUserId());
            Kind kind = kindService.getById(material.getKindId());
            Integer starAvg = commentService.getStarAvg(material.getId());
            List<Comment> comments = commentService.listComments(Comment.builder().materialId(material.getId()).build());
            materialVos.add(MaterialVo.builder()
                    .material(material).author(user).kind(kind).comments(comments.size()).imgs(dealImgs(material)).starAvg(starAvg)
                    .build());
        }
        return materialVos;
    }

    private List<MessageVo> convertMessageVo(List<Message> messages){
        List<MessageVo> messageVos = new ArrayList<>();
        for (Message message : messages) {
            User user = userService.getById(message.getSentUserId());
            Material material = materialService.getById(message.getMaterialId());
            messageVos.add(MessageVo.builder().material(material).message(message).sender(user).build());
        }
        return messageVos;
    }

    private MaterialVo convertMaterialVo(Material material){
        User user = userService.getById(material.getUserId());
        Kind kind = kindService.getById(material.getKindId());
        Integer starAvg = commentService.getStarAvg(material.getId());
        List<Comment> comments = commentService.listComments(Comment.builder().materialId(material.getId()).build());
        MaterialVo materialVo = MaterialVo.builder()
                .material(material).author(user).kind(kind).comments(comments.size()).imgs(dealImgs(material)).starAvg(starAvg)
                .build();
        return materialVo;
    }

    /**
     * 获取需要在前端展示的素材预览图+封面集合
     */
    private List<String> dealImgs(Material material){
        String string = material.getImgs();
        List<String> list = new ArrayList<>();
        list.add(material.getImg());
        JSONArray jsonArray= JSONArray.parseArray(string);
        for(int i= 0;i < jsonArray.size();i++){
            String path = (String) jsonArray.get(i);
            list.add(path);
        }
        return list;
    }

    /**
     * 处理一级评论
     */
    private List<CommentVo> dealFirstLevelComment(List<Comment> comments){
        ArrayList<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments) {
            User user = userService.getById(comment.getUserId());
            List<Comment> list = commentService.listSecondLevel(comment.getId());
            CommentVo commentVo = CommentVo.builder()
                    .comment(comment).user(user).replyComments(dealSecondLevelComment(list))
                    .build();
            commentVos.add(commentVo);
        }
        return commentVos;
    }


    /**
     * 处理二级评论
     */
    private List<CommentVo> dealSecondLevelComment(List<Comment> comments){
        ArrayList<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments) {
            User user = userService.getById(comment.getUserId());
            CommentVo commentVo = CommentVo.builder()
                    .comment(comment).user(user)
                    .build();
            commentVos.add(commentVo);
        }
        return commentVos;
    }
}
