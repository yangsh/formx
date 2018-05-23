package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yangsh on 2018-05-18
 */
@Mapper
public interface ShopMapper {

    /** 获取店铺 */
    Shop get(@Param("shopId") Integer shopId);

}
