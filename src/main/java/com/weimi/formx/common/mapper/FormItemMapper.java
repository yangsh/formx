package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.FormItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@Mapper
public interface FormItemMapper {

    /** 获取表单项列表 */
    List<FormItem> getList(@Param("formId") Integer formId);

}
