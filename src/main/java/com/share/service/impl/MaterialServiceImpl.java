package com.share.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.share.constant.CheckedStatusType;
import com.share.constant.TrashStatusType;
import com.share.entity.Comment;
import com.share.entity.Kind;
import com.share.entity.Material;
import com.share.entity.User;
import com.share.mapper.MaterialMapper;
import com.share.service.MaterialService;
import com.share.vo.MaterialVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 莫提
 * @ClassName MaterialServiceImpl
 * @Description (Material)表业务接口实现类
 * @date 2020-11-01 18:34:40
 * @Version 1.0
 **/
@Service("materialService")
public class MaterialServiceImpl extends BaseService implements MaterialService {

    @Override
    public boolean insert(Material material) {
        if (materialMapper.insert(material) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        if (materialMapper.deleteById(id) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Material getById(Integer id) {
        return materialMapper.getById(id);
    }

    @Override
    public Material getVoById(Integer id) {
        Material material = materialMapper.getById(id);
        return material;
    }

    @Override
    public List<Material> listMaterials() {
        return materialMapper.listMaterials();
    }

    @Override
    public List<Material> listMaterials(Material material) {
        return materialMapper.listMaterials(material);
    }

    @Override
    public List<Material> listMaterialsByName(String name) {
        return materialMapper.listMaterialsByName(name);
    }

    @Override
    public List<Material> listMaterialsByUserId(Integer uId) {
        return materialMapper.listMaterialsByUserId(uId);
    }

    @Override
    public List<Material> listMaterialsByKindIdorderByDownloadCount(Integer kindId) {
        return materialMapper.listMaterialsByKindIdorderByDownloadCount(kindId);
    }

    @Override
    public List<Material> listMaterialsByKindIdorderByDownloadCount(Integer kindId, Integer mId) {
        return materialMapper.listMaterialsByKindIdorderByDownloadCountExclude(kindId,mId);
    }

    @Override
    public boolean update(Material material) {
        if (materialMapper.update(material) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<Material> listIndexMaterials() {
        Material material = Material.builder()
                .isChecked(CheckedStatusType.PASS).status(TrashStatusType.NOT_IN)
                .build();
        List<Material> materials = materialMapper.listMaterials(material);
        return materials;
    }

    @Override
    public int countMaterials(Material material) {
        return materialMapper.countMaterials(material);
    }

    @Override
    public List<Material> orderByDownloadCount(Integer kId) {
        return materialMapper.orderByDownloadCount(kId);
    }

}