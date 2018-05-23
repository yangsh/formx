package com.weimi.formx.michelin.controller;

import com.weimi.formx.common.request.EmptyRequest;
import com.weimi.formx.common.response.ResponseData;
import com.weimi.formx.michelin.response.CompleteShopListForAdviserResponse;
import com.weimi.formx.michelin.response.CompleteShopListForShopkeeperResponse;
import com.weimi.formx.michelin.response.NotCompleteShopListResponse;
import com.weimi.formx.michelin.response.ShopDataResponse;
import com.weimi.formx.michelin.service.ShopService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by yangsh on 2018-05-20
 */
@RestController
@RequestMapping("/mcl")
public class ShopController {

    @Resource
    private ShopService shopService;

    /**
     * 店主获取店铺完成情况统计数据
     */
    @GetMapping("/shopkeeper/shop/data")
    public ResponseData<ShopDataResponse> getShopDataForShopkeeper(@Valid EmptyRequest req, BindingResult result) {
        return ResponseData.success(shopService.getShopData());
    }

    /**
     * 店主获取已完成店铺列表
     */
    @GetMapping("/shopkeeper/shop/complete/list")
    public ResponseData<List<CompleteShopListForShopkeeperResponse>> getCompleteShopListForShopkeeper(@Valid EmptyRequest req, BindingResult result) {
        return ResponseData.success(shopService.getCompleteShopListForShopkeeper());
    }

    /**
     * 店主获取未完成店铺列表
     */
    @GetMapping("/shopkeeper/shop/notcomplete/list")
    public ResponseData<List<NotCompleteShopListResponse>> getNotCompleteShopListForShopkeeper(@Valid EmptyRequest req, BindingResult result) {
        return ResponseData.success(shopService.getNotCompleteShopList());
    }

    /**
     * 顾问获取店铺确认情况统计数据
     */
    @GetMapping("/adviser/shop/data")
    public ResponseData<ShopDataResponse> getShopDataForAdviser(@Valid EmptyRequest req, BindingResult result) {
        return ResponseData.success(shopService.getShopData());
    }

    /**
     * 顾问获取已完成店铺列表
     */
    @GetMapping("/adviser/shop/complete/list")
    public ResponseData<List<CompleteShopListForAdviserResponse>> getCompleteShopListForAdviser(@Valid EmptyRequest req, BindingResult result) {
        return ResponseData.success(shopService.getCompleteShopListForAdviser());
    }

    /**
     * 顾问获取未完成店铺列表
     */
    @GetMapping("/adviser/shop/notcomplete/list")
    public ResponseData<List<NotCompleteShopListResponse>> getNotCompleteShopListForAdviser(@Valid EmptyRequest req, BindingResult result) {
        return ResponseData.success(shopService.getNotCompleteShopList());
    }

}
