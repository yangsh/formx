package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.UserShopRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@Mapper
public interface UserShopRelationMapper {

    /** 获取用户店铺列表 */
    List<UserShopRelation> getList(@Param("userId") String userId);

}
