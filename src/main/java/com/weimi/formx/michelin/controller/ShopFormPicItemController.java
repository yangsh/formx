package com.weimi.formx.michelin.controller;

import com.weimi.formx.common.response.EmptyResponse;
import com.weimi.formx.common.response.ResponseData;
import com.weimi.formx.michelin.request.ShopFormExecutionIdRequest;
import com.weimi.formx.michelin.request.ShopFormPicItemCommentRequest;
import com.weimi.formx.michelin.response.ShopFormPicItemListForShopownerResponse;
import com.weimi.formx.michelin.response.ShopFormPicItemListResponse;
import com.weimi.formx.michelin.service.ShopFormPicItemService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by yangsh on 2018-05-19
 */
@RestController
@RequestMapping("/mcl")
public class ShopFormPicItemController {

    @Resource
    private ShopFormPicItemService shopFormPicItemService;

    /**
     * 店长获取店铺表单图片项列表
     */
    @GetMapping("/shopowner/shopformpicitem/list")
    public ResponseData<ShopFormPicItemListForShopownerResponse> listForShopowner(@Valid ShopFormExecutionIdRequest req, BindingResult result) {
        return ResponseData.success(shopFormPicItemService.getShopFormPicItemListForShopowner(req.getShopFormExecutionId()));
    }

    /**
     * 店主获取店铺表单图片项列表
     */
    @GetMapping("/shopkeeper/shopformpicitem/list")
    public ResponseData<ShopFormPicItemListResponse> listForShopkeeper(@Valid ShopFormExecutionIdRequest req, BindingResult result) {
        return ResponseData.success(shopFormPicItemService.getShopFormPicItemList(req.getShopFormExecutionId()));
    }

    /**
     * 顾问获取店铺表单图片项列表
     */
    @GetMapping("/adviser/shopformpicitem/list")
    public ResponseData<ShopFormPicItemListResponse> listForAdviser(@Valid ShopFormExecutionIdRequest req, BindingResult result) {
        return ResponseData.success(shopFormPicItemService.getShopFormPicItemList(req.getShopFormExecutionId()));
    }

    /**
     * 店主评论图片项
     */
    @PostMapping("/shopformpicitem/comment")
    public ResponseData<EmptyResponse> comment(@Valid ShopFormPicItemCommentRequest req, BindingResult result) {
        shopFormPicItemService.comment(req.getShopFormPicItemId(), req.getPicComment());
        return ResponseData.success();
    }

}
