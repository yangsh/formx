package com.weimi.formx.michelin.controller;

import com.weimi.formx.common.response.EmptyResponse;
import com.weimi.formx.common.response.ResponseData;
import com.weimi.formx.michelin.request.ShopFormExecutionIdRequest;
import com.weimi.formx.michelin.request.ShopFormTextItemIdRequest;
import com.weimi.formx.michelin.response.ShopFormTextItemListForShopownerResponse;
import com.weimi.formx.michelin.response.ShopFormTextItemListResponse;
import com.weimi.formx.michelin.service.ShopFormTextItemService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by yangsh on 2018-05-18
 */
@RestController
@RequestMapping("/mcl")
public class ShopFormTextItemController {

    @Resource
    private ShopFormTextItemService shopFormTextItemService;

    /**
     * 店长获取店铺表单文本项列表
     */
    @GetMapping("/shopowner/shopformtextitem/list")
    public ResponseData<ShopFormTextItemListForShopownerResponse> listForShopowner(@Valid ShopFormExecutionIdRequest req, BindingResult result) {
        return ResponseData.success(shopFormTextItemService.getShopFormTextItemListForShopowner(req.getShopFormExecutionId()));
    }

    /**
     * 店主获取店铺表单文本项列表
     */
    @GetMapping("/shopkeeper/shopformtextitem/list")
    public ResponseData<ShopFormTextItemListResponse> listForShopkeeper(@Valid ShopFormExecutionIdRequest req, BindingResult result) {
        return ResponseData.success(shopFormTextItemService.getShopFormTextItemList(req.getShopFormExecutionId()));
    }

    /**
     * 顾问获取店铺表单文本项列表
     */
    @GetMapping("/adviser/shopformtextitem/list")
    public ResponseData<ShopFormTextItemListResponse> listForAdviser(@Valid ShopFormExecutionIdRequest req, BindingResult result) {
        return ResponseData.success(shopFormTextItemService.getShopFormTextItemList(req.getShopFormExecutionId()));
    }

    /**
     * 店长执行店铺表单项
     */
    @PostMapping("/shopformtextitem/execute")
    public ResponseData<EmptyResponse> execute(@Valid ShopFormTextItemIdRequest req, BindingResult result) {
        shopFormTextItemService.executeShopFormTextItem(req.getShopFormTextItemId());
        return ResponseData.success();
    }

}
