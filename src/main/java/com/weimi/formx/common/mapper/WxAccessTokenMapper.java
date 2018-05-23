package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.WxAccessToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * Created by yangsh on 2018-05-17
 */
@Mapper
public interface WxAccessTokenMapper {

    /** 新增 accessToken */
    void add(WxAccessToken wxAccessToken);

    /** 获取 accessToken */
    WxAccessToken get();

    /** 更新 accessToken */
    @Update("UPDATE t_wx_access_token SET access_token=#{accessToken}, update_time=#{updateTime} WHERE id=${id}")
    void update(@Param("accessToken") String accessToken, @Param("updateTime") Date updateTime, @Param("id") Long id);

}
