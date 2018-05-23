package com.weimi.formx.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weimi.formx.wx.base.config.Constant;
import com.weimi.formx.wx.base.config.WxUrl;
import com.weimi.formx.wx.base.response.WxAccessTokenResponse;
import com.weimi.formx.wx.base.response.WxJsapiTicketResponse;
import com.weimi.formx.wx.base.response.WxUserInfoResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by yangsh on 2018-05-17
 */
@Slf4j
public class WxUtils {

    private WxUtils() {}

    /**
     * 获取微信 access_token
     * @return
     */
    public static String getToken() {
        HashMap<String, String> param = new HashMap<>();

        param.put("corpid", Constant.CORPID);
        param.put("corpsecret", Constant.SECRET);

        ObjectMapper om = new ObjectMapper();

        String token = "";
        try {
            String json = HttpsUtils.get(WxUrl.ACCESS_TOKEN_URL, param);
            log.info("token json: {}", json);
            WxAccessTokenResponse watr = om.readValue(json, WxAccessTokenResponse.class);
            if (watr != null && Constant.ERRCODE_SUCCESS.equals(watr.getErrcode())) {
                token = watr.getAccess_token();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     * 获取微信 jsapi_tiket
     * @param token
     * @return
     */
    public static String getTicket(final String token) {
        HashMap<String, String> param = new HashMap<>();

        param.put("access_token", token);

        ObjectMapper om = new ObjectMapper();

        String ticket = "";
        try {
            String json = HttpsUtils.get(WxUrl.JSAPI_TICKET_URL, param);
            log.info("ticket json: {}", json);
            WxJsapiTicketResponse wjtr = om.readValue(json, WxJsapiTicketResponse.class);
            if (wjtr != null && Constant.ERRCODE_SUCCESS.equals(wjtr.getErrcode())) {
                ticket = wjtr.getTicket();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticket;
    }

    /**
     * 获取 userId
     * @param token
     * @param code
     * @return
     */
    public static String getUserId(final String token, final String code) {
        HashMap<String, String> param = new HashMap<>();

        param.put("access_token", token);
        param.put("code", code);

        ObjectMapper om = new ObjectMapper();

        String userId = "";
        try {
            String json = HttpsUtils.get(WxUrl.USER_INFO_URL, param);
            log.info("user info json: {}", json);
            json = json.replace("UserId", "userId").replace("DeviceId", "deviceId");
            WxUserInfoResponse wuir = om.readValue(json, WxUserInfoResponse.class);
            if (wuir != null && Constant.ERRCODE_SUCCESS.equals(wuir.getErrcode())) {
                userId = wuir.getUserId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userId;
    }

    /**
     * 获取下载图片 url
     */
    public static InputStream getPicUrl(final String token, final String serverId) {
        HashMap<String, String> param = new HashMap<>();

        param.put("access_token", token);
        param.put("media_id", serverId);

        InputStream is = null;
        try {
            is = HttpsUtils.downloadPic(WxUrl.DOWNLOAD_PIC, param);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is;
    }

}
