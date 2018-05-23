package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.WxJsapiTiket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * Created by yangsh on 2018-05-17
 */
@Mapper
public interface WxJsapiTiketMapper {

    /** 新增 jsapiTiket */
    void add(WxJsapiTiket WxJsapiTiket);

    /** 获取 jsapiTiket */
    WxJsapiTiket get();

    /** 更新 jsapiTiket */
    @Update("UPDATE t_wx_jsapi_tiket SET jsapi_tiket=#{jsapiTiket}, update_time=#{updateTime} WHERE id=${id}")
    void update(@Param("jsapiTiket") String jsapiTiket, @Param("updateTime") Date updateTime, @Param("id") Long id);

}
