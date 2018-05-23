package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.Form;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@Mapper
public interface FormMapper {

    /** 获取表单列表 */
    List<Form> getList();

}
