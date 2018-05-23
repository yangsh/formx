package com.weimi.formx.michelin.service;

import com.weimi.formx.common.entity.UserShopRelation;
import com.weimi.formx.common.mapper.UserShopRelationMapper;
import com.weimi.formx.common.response.ResponseCode;
import com.weimi.formx.exception.FormxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@Slf4j
@Service
public class UserShopRelationService {

    @Resource
    private UserShopRelationMapper userShopRelationMapper;

    /**
     * 获取店长店铺ID
     * @param userId 用户ID
     * @return 店铺ID
     */
    public Integer getShopOwnerShopId(final String userId) {
        List<UserShopRelation> list = userShopRelationMapper.getList(userId);
        if (list.isEmpty()) {
            log.error("can't get shopId by userId={}", userId);
            throw new FormxException(ResponseCode.DATA_EXCEPTION, "店长必须关联一个店铺");
        }
        if (list.size() > 1) {
            log.error("get more one shopId by userId={}", userId);
            throw new FormxException(ResponseCode.DATA_EXCEPTION, "店长只能关联一个店铺");
        }
        return list.get(0).getShopId();
    }

}
