package com.weimi.formx.michelin.service;

import com.weimi.formx.common.entity.ShopFormExecution;
import com.weimi.formx.common.entity.ShopFormPicItem;
import com.weimi.formx.common.mapper.ShopFormExecutionMapper;
import com.weimi.formx.common.mapper.ShopFormPicItemMapper;
import com.weimi.formx.michelin.response.ShopFormPicItemListResponse;
import com.weimi.formx.michelin.response.ShopFormPicItemListForShopownerResponse;
import com.weimi.formx.michelin.response.ShopFormPicItemResponse;
import com.weimi.formx.util.DateUtils;
import com.weimi.formx.util.OSSUtils;
import com.weimi.formx.util.ValueCheckUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangsh on 2018-05-19
 */
@Service
public class ShopFormPicItemService {

    @Resource
    private ShopFormPicItemMapper shopFormPicItemMapper;
    @Resource
    private ShopFormExecutionMapper shopFormExecutionMapper;

    @Resource
    private HttpSession httpSession;

    @Transactional
    public ShopFormPicItemListForShopownerResponse getShopFormPicItemListForShopowner(final Long shopFormExecutionId) {
        Date now = new Date();

        ShopFormExecution shopFormExecution = shopFormExecutionMapper.get(shopFormExecutionId);
        ValueCheckUtils.notNull(shopFormExecution, "店铺表单执行为空");

        List<ShopFormPicItemResponse> inResList = new ArrayList<>();
        ShopFormPicItemResponse inRes = null;

        OSSUtils oss = new OSSUtils();
        List<ShopFormPicItem> list = shopFormPicItemMapper.getList(shopFormExecutionId, DateUtils.toDateString(now));
        for (int i = 0; i < list.size(); i++) {
            inRes = new ShopFormPicItemResponse();
            BeanUtils.copyProperties(list.get(i), inRes);
            inRes.setPicUrl(oss.getUrl(list.get(i).getPicName()));
            inResList.add(inRes);
        }

        oss.destory();

        ShopFormPicItemListForShopownerResponse res = new ShopFormPicItemListForShopownerResponse();

        res.setFormName(shopFormExecution.getFormName());
        res.setReportStatus(shopFormExecution.getReportStatus());
        res.setConfirmStatus(shopFormExecution.getConfirmStatus());
        res.setComment(shopFormExecution.getComment());
        res.setShopFormPicItemList(inResList);

        return res;
    }

    @Transactional
    public ShopFormPicItemListResponse getShopFormPicItemList(final Long shopFormExecutionId) {
        Date now = new Date();

        ShopFormExecution shopFormExecution = shopFormExecutionMapper.get(shopFormExecutionId);
        ValueCheckUtils.notNull(shopFormExecution, "店铺表单执行为空");

        List<ShopFormPicItemResponse> inResList = new ArrayList<>();
        ShopFormPicItemResponse inRes = null;

        OSSUtils oss = new OSSUtils();
        List<ShopFormPicItem> list = shopFormPicItemMapper.getList(shopFormExecutionId, DateUtils.toDateString(now));
        for (int i = 0; i < list.size(); i++) {
            inRes = new ShopFormPicItemResponse();
            BeanUtils.copyProperties(list.get(i), inRes);
            inRes.setPicUrl(oss.getUrl(list.get(i).getPicName()));
            inResList.add(inRes);
        }

        oss.destory();

        ShopFormPicItemListResponse res = new ShopFormPicItemListResponse();

        res.setShopId(shopFormExecution.getShopId());
        res.setFormName(shopFormExecution.getFormName());
        res.setConfirmStatus(shopFormExecution.getConfirmStatus());
        res.setComment(shopFormExecution.getComment());
        res.setShopFormPicItemList(inResList);

        return res;
    }

    public void comment(Long shopFormPicItemId, String picComment) {
        shopFormPicItemMapper.updatePicComment(picComment, shopFormPicItemId);
    }

}
