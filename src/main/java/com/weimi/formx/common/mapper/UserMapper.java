package com.weimi.formx.common.mapper;

import com.weimi.formx.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yangsh on 2018-05-17
 */
@Mapper
public interface UserMapper {

    /** 新增用户 */
    void add(User user);

    /** 获取用户 */
    User get(@Param("userId") String userId);

}
