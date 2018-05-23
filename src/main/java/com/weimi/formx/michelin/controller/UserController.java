package com.weimi.formx.michelin.controller;

import com.weimi.formx.common.response.ResponseData;
import com.weimi.formx.michelin.request.UrlRequest;
import com.weimi.formx.michelin.response.RoleIdResponse;
import com.weimi.formx.michelin.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by yangsh on 2018-05-20
 */
@RestController
@RequestMapping("/mcl")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取角色ID
     */
    @GetMapping("/user/roleid")
    public ResponseData<RoleIdResponse> getRoleId(@Valid UrlRequest req, BindingResult result) {
        return ResponseData.success(userService.getRoleId(req.getUrl()));
    }

}
