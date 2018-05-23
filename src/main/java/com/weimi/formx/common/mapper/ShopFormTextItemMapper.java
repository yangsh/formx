package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.ShopFormTextItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@Mapper
public interface ShopFormTextItemMapper {

    /** 新增店铺表单项 */
    void add(ShopFormTextItem shopFormTextItem);

    /** 获取店铺表单项列表 */
    List<ShopFormTextItem> getList(@Param("shopFormExecutionId") Long shopFormExecutionId, @Param("createTime") String createTime);

    /** 修改执行状态 */
    @Update("UPDATE t_shop_form_text_item SET execute_status=#{executeStatus}, execute_time=#{executeTime} WHERE shop_form_text_item_id=#{shopFormTextItemId}")
    void updateExecuteStatus(@Param("executeStatus") String executeStatus, @Param("executeTime") Date executeTime, @Param("shopFormTextItemId") Long shopFormTextItemId);

}
