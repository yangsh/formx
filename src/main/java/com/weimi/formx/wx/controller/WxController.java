package com.weimi.formx.wx.controller;

import com.weimi.formx.common.response.ResponseData;
import com.weimi.formx.wx.request.WxPicDownloadRequest;
import com.weimi.formx.wx.request.WxSignatureRequest;
import com.weimi.formx.wx.response.WxPicDownloadResponse;
import com.weimi.formx.wx.response.WxSignatureResponse;
import com.weimi.formx.wx.service.WxService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by yangsh on 2018-05-13
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @Resource
    private WxService wxService;

    /**
     * 获取微信 JS-SDK 签名
     */
    @GetMapping("/signature")
    public ResponseData<WxSignatureResponse> getSignature(@Valid WxSignatureRequest req, BindingResult result) {
        return ResponseData.success(wxService.getSignature(req));
    }

    @PostMapping("/pic/download")
    public ResponseData<WxPicDownloadResponse> downloadPic(@Valid WxPicDownloadRequest req, BindingResult result) {
        return ResponseData.success(wxService.downloadPic(req.getShopFormExecutionId(), req.getServerIds()));
    }

}
