package com.weimi.formx.michelin.controller;

import com.weimi.formx.common.response.EmptyResponse;
import com.weimi.formx.common.response.ResponseData;
import com.weimi.formx.michelin.request.ShopFormExecutionCommentRequest;
import com.weimi.formx.michelin.request.ShopFormExecutionIdRequest;
import com.weimi.formx.michelin.request.ShopIdRequest;
import com.weimi.formx.michelin.response.ShopFormExecutionListResponse;
import com.weimi.formx.michelin.response.ShopFormExecutionResponse;
import com.weimi.formx.michelin.service.ShopFormExecutionService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@RestController
@RequestMapping("/mcl")
public class ShopFormExecutionController {

    @Resource
    private ShopFormExecutionService shopFormExecutionService;

    /**
     * 店长获取表单执行列表
     */
    @GetMapping("/shopowner/shopformexecution/list")
    public ResponseData<List<ShopFormExecutionResponse>> listForShopowner(@Valid EmptyResponse req, BindingResult result) {
        return ResponseData.success(shopFormExecutionService.getShopFormExecutionListForShopowner());
    }

    /**
     * 店主获取表单执行列表
     */
    @GetMapping("/shopkeeper/shopformexecution/list")
    public ResponseData<ShopFormExecutionListResponse> listForShopkeeper(@Valid ShopIdRequest req, BindingResult result) {
        return ResponseData.success(shopFormExecutionService.getShopFormExecutionList(req.getShopId()));
    }

    /**
     * 顾问获取表单执行列表
     */
    @GetMapping("/adviser/shopformexecution/list")
    public ResponseData<ShopFormExecutionListResponse> listForAdviser(@Valid ShopIdRequest req, BindingResult result) {
        return ResponseData.success(shopFormExecutionService.getShopFormExecutionList(req.getShopId()));
    }

    /**
     * 店长提交表单
     */
    @PostMapping("/shopformexecution/report")
    public ResponseData<EmptyResponse> report(@Valid ShopFormExecutionIdRequest res, BindingResult result) {
        shopFormExecutionService.reportShopFormExecution(res.getShopFormExecutionId());
        return ResponseData.success();
    }

    /**
     * 店主确认表单
     */
    @PostMapping("/shopformexecution/confirm")
    public ResponseData<EmptyResponse> confirm(@Valid ShopFormExecutionIdRequest res, BindingResult result) {
        shopFormExecutionService.confirmShopFormExecution(res.getShopFormExecutionId());
        return ResponseData.success();
    }

    /**
     * 顾问评论表单
     */
    @PostMapping("/shopformexecution/comment")
    public ResponseData<EmptyResponse> comment(@Valid ShopFormExecutionCommentRequest res, BindingResult result) {
        shopFormExecutionService.comment(res.getShopFormExecutionId(), res.getComment());
        return ResponseData.success();
    }

}