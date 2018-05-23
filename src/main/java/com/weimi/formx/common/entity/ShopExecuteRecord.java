package com.weimi.formx.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 店铺执行记录
 * Created by yangsh on 2018-05-19
 */
@Getter
@Setter
public class ShopExecuteRecord {

    private Long shopExecuteRecordId; // 店铺执行记录ID
    private Integer shopId; // 店铺ID
    private String shopName; // 店铺名称
    private Date completeTime; // 完成时间
    private String confirmStatus; // 确认状态: 0-未确认; 1-已确认
    private Date confirmTime; // 确认时间

}
