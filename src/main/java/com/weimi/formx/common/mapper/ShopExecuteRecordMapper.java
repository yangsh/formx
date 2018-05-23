package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.ShopExecuteRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * Created by yangsh on 2018-05-19
 */
@Mapper
public interface ShopExecuteRecordMapper {

    /** 新增店铺执行记录 */
    void add(ShopExecuteRecord shopExecuteRecord);

    /** 获取店铺执行记录 */
    @Select("SELECT * FROM t_shop_execute_record WHERE shop_id=#{shopId} AND DATE_FORMAT(complete_time, '%Y-%m-%d')=#{completeTime} LIMIT 1")
    ShopExecuteRecord get(@Param("shopId") Integer shopId, @Param("completeTime") String completeTime);

    /** 获取完成确认店铺 */
    @Select("SELECT * FROM t_shop_execute_record WHERE shop_id=#{shopId} AND DATE_FORMAT(complete_time, '%Y-%m-%d')=#{completeTime} AND confirm_status='1' LIMIT 1")
    ShopExecuteRecord getConfirmShop(@Param("shopId") Integer shopId, @Param("completeTime") String completeTime);

    /** 修改确认状态 */
    void updateConfirmStatus(
            @Param("confirmStatus") String confirmStatus,
            @Param("confirmTime") Date confirmTime,
            @Param("shopId") Integer shopId,
            @Param("completeTime") String completeTime);


}
