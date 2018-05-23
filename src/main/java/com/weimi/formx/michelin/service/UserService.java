package com.weimi.formx.michelin.service;

import com.weimi.formx.common.entity.User;
import com.weimi.formx.common.enumeration.RoleEnum;
import com.weimi.formx.common.mapper.UserMapper;
import com.weimi.formx.common.response.ResponseCode;
import com.weimi.formx.exception.FormxException;
import com.weimi.formx.michelin.response.RoleIdResponse;
import com.weimi.formx.util.UrlUtils;
import com.weimi.formx.util.ValueCheckUtils;
import com.weimi.formx.util.WxUtils;
import com.weimi.formx.wx.base.config.Constant;
import com.weimi.formx.wx.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by yangsh on 2018-05-18
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private WxService wxService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private HttpSession httpSession;

    /**
     * 获取角色ID
     * @param url URL
     * @return
     */
    public RoleIdResponse getRoleId(final String url) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            String userId = "samyangsh@163.com";
            ValueCheckUtils.notEmpty(userId, "用户ID获取失败");
            user = getUser(userId);
            httpSession.setAttribute("user", user);
        }

        Integer roleId = user.getRoleId();
        ValueCheckUtils.notNull(roleId, "用户角色ID不能为空");

        RoleIdResponse res = new RoleIdResponse();

        res.setRoleId(roleId);

        return res;
    }

    /**
     * 获取用户ID
     * @param url URL
     * @return 用户ID
     */
    public String getUserId(final String url) {
        Map<String, String> urlParam = UrlUtils.getUrlParam(url);

        final String state = urlParam.get("state");

        if (!Constant.STATE.equals(state)) {
            log.error("state expect to {} but {}", Constant.STATE, state);
            throw new FormxException(ResponseCode.PARAM_ERROR);
        }

        return WxUtils.getUserId(wxService.getToken(), urlParam.get("code"));
    }

    /**
     * 获取用户
     * @param userId 用户ID
     * @return 用户
     */
    public User getUser(final String userId) {
        User user = userMapper.get(userId);
        if (user == null) {
            user = new User();

            user.setUserId(userId);
            user.setName("");
            user.setRoleId(RoleEnum.SHOPOWNER.getRoleId());

            userMapper.add(user);
        }

        return user;
    }

}
