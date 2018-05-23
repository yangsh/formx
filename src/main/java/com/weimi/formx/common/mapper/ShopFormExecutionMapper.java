package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.ShopFormExecution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@Mapper
public interface ShopFormExecutionMapper {

    /** 新增店铺表单执行 */
    void add(ShopFormExecution shopFormExecution);

    /** 获取店铺表单执行 */
    ShopFormExecution get(@Param("shopFormExecutionId") Long shopFormExecutionId);

    /** 获取店铺表单执行列表 */
    List<ShopFormExecution> getList(@Param("shopId") Integer shopId, @Param("createTime") String createTime);

    /** 修改汇报状态 */
    void updateReportStatus(@Param("reportStatus") String reportStatus, @Param("reportTime") Date reportTime, @Param("shopFormExecutionId") Long shopFormExecutionId, @Param("createTime") String createTime);

    /** 修改确认状态 */
    void updateConfirmStatus(@Param("confirmStatus") String confirmStatus, @Param("confirmTime") Date confirmTime, @Param("shopFormExecutionId") Long shopFormExecutionId, @Param("createTime") String createTime);

    /** 修改评论 */
    void updateComment(@Param("comment") String comment, @Param("shopFormExecutionId") Long shopFormExecutionId, @Param("createTime") String createTime);

    /** 获取表单完成数量 */
    @Select("SELECT count(*) FROM t_shop_form_execution WHERE shop_id=#{shopId} AND DATE_FORMAT(create_time, '%Y-%m-%d')=#{createTime} AND report_status='1'")
    Integer getFormCompleteCount(@Param("shopId") Integer shopId, @Param("createTime") String createTime);

}
