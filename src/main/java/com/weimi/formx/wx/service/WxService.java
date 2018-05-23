package com.weimi.formx.wx.service;

import com.weimi.formx.common.entity.ShopFormPicItem;
import com.weimi.formx.common.entity.WxAccessToken;
import com.weimi.formx.common.entity.WxJsapiTiket;
import com.weimi.formx.common.mapper.ShopFormPicItemMapper;
import com.weimi.formx.common.mapper.WxAccessTokenMapper;
import com.weimi.formx.common.mapper.WxJsapiTiketMapper;
import com.weimi.formx.util.EncryptUtils;
import com.weimi.formx.util.OSSUtils;
import com.weimi.formx.util.StringUtils;
import com.weimi.formx.util.WxUtils;
import com.weimi.formx.wx.base.config.Constant;
import com.weimi.formx.wx.request.WxSignatureRequest;
import com.weimi.formx.wx.response.WxPicDownloadResponse;
import com.weimi.formx.wx.response.WxSignatureResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangsh on 2018-05-15
 */
@Slf4j
@Service
public class WxService {

    @Resource
    private WxAccessTokenMapper wxAccessTokenMapper;
    @Resource
    private WxJsapiTiketMapper wxJsapiTiketMapper;
    @Resource
    private ShopFormPicItemMapper shopFormPicItemMapper;

    /**
     * 获取 token
     */
    public String getToken() {
        Date now = new Date();

        WxAccessToken wxAccessToken = wxAccessTokenMapper.get();
        if (wxAccessToken == null) {
            String token = WxUtils.getToken();

            wxAccessToken = new WxAccessToken();

            wxAccessToken.setAccessToken(token);
            wxAccessToken.setCreateTime(now);
            wxAccessToken.setUpdateTime(now);

            wxAccessTokenMapper.add(wxAccessToken);

            return token;
        }

        Date updateTime = wxAccessToken.getUpdateTime();
        if (((now.getTime() - updateTime.getTime())/1000) > Constant.TOKEN_EXPIRE_SECOND) {
            String token = WxUtils.getToken();

            wxAccessTokenMapper.update(token, now, wxAccessToken.getId());

            return token;
        }

        return wxAccessToken.getAccessToken();
    }

    /**
     * 获取 ticket
     */
    private String getTicket() {
        Date now = new Date();

        WxJsapiTiket wxJsapiTiket = wxJsapiTiketMapper.get();
        if (wxJsapiTiket == null) {
            String token = getToken();
            String ticket = WxUtils.getTicket(token);

            wxJsapiTiket = new WxJsapiTiket();

            wxJsapiTiket.setJsapiTiket(ticket);
            wxJsapiTiket.setCreateTime(now);
            wxJsapiTiket.setUpdateTime(now);

            wxJsapiTiketMapper.add(wxJsapiTiket);

            return ticket;
        }

        Date updateTime = wxJsapiTiket.getUpdateTime();
        if (((now.getTime() - updateTime.getTime())/1000) > Constant.TIKET_EXPIRE_SECOND) {
            String token = getToken();
            String ticket = WxUtils.getTicket(token);

            wxJsapiTiketMapper.update(ticket, now, wxJsapiTiket.getId());

            return ticket;
        }

        return wxJsapiTiket.getJsapiTiket();
    }

    /**
     * 获取签名信息
     */
    public WxSignatureResponse getSignature(WxSignatureRequest req) {
        final String url = req.getUrl();

        String ticket = getTicket();

        String noncestr = StringUtils.getRandomString(16);
        String timestamp = String.valueOf(System.currentTimeMillis());

        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=").append(ticket);
        sb.append("&noncestr=").append(noncestr);
        sb.append("&timestamp=").append(timestamp);
        sb.append("&url=").append(url);

        WxSignatureResponse res = new WxSignatureResponse();

        res.setAppId(Constant.CORPID);
        res.setNonceStr(noncestr);
        res.setTimestamp(timestamp);
        res.setSignature(EncryptUtils.getSHA1(sb.toString()));

        return res;
    }

    public WxPicDownloadResponse downloadPic(Long shopFormExecutionId, List<String> serverIds) {
        String token = getToken();

        InputStream is = WxUtils.getPicUrl(token, serverIds.get(0));

        OSSUtils oss = new OSSUtils();
        String picName = oss.uploadImage(is);

        ShopFormPicItem shopFormPicItem = new ShopFormPicItem();

        shopFormPicItem.setShopFormExecutionId(shopFormExecutionId);
        shopFormPicItem.setPicName(picName);
        shopFormPicItem.setCreateTime(new Date());

        shopFormPicItemMapper.add(shopFormPicItem);

        List<String> picUrls = new ArrayList<>();

        picUrls.add(oss.getUrl(picName));

        oss.destory();

        WxPicDownloadResponse res = new WxPicDownloadResponse();

        res.setPicUrls(picUrls);

        return res;
    }

}
