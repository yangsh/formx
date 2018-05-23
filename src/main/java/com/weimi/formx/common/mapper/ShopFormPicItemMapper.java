package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.ShopFormPicItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by yangsh on 2018-05-19
 */
@Mapper
public interface ShopFormPicItemMapper {

    /** 新增店铺表单图片项 */
    void add(ShopFormPicItem shopFormPicItem);

    /** 获取店铺表单图片项列表 */
    List<ShopFormPicItem> getList(@Param("shopFormExecutionId") Long shopFormExecutionId, @Param("createTime") String createTime);

    /** 修改图片评论 */
    @Update("UPDATE t_shop_form_pic_item SET pic_comment=#{picComment} WHERE shop_form_pic_item_id=#{shopFormPicItemId}")
    void updatePicComment(@Param("picComment") String picComment, @Param("shopFormPicItemId") Long shopFormPicItemId);

}
