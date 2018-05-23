package com.weimi.formx.michelin.service;

import com.weimi.formx.common.entity.FormItem;
import com.weimi.formx.common.entity.ShopFormExecution;
import com.weimi.formx.common.entity.ShopFormTextItem;
import com.weimi.formx.common.enumeration.ExecuteStatusEnum;
import com.weimi.formx.common.mapper.FormItemMapper;
import com.weimi.formx.common.mapper.ShopFormExecutionMapper;
import com.weimi.formx.common.mapper.ShopFormTextItemMapper;
import com.weimi.formx.michelin.response.ShopFormTextItemListForShopownerResponse;
import com.weimi.formx.michelin.response.ShopFormTextItemListResponse;
import com.weimi.formx.michelin.response.ShopFormTextItemResponse;
import com.weimi.formx.util.DateUtils;
import com.weimi.formx.util.ValueCheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangsh on 2018-05-18
 */
@Slf4j
@Service
public class ShopFormTextItemService {

    @Resource
    private ShopFormTextItemMapper shopFormTextItemMapper;
    @Resource
    private ShopFormExecutionMapper shopFormExecutionMapper;
    @Resource
    private FormItemMapper formItemMapper;

    @Resource
    private HttpSession httpSession;

    @Transactional
    public ShopFormTextItemListForShopownerResponse getShopFormTextItemListForShopowner(final Long shopFormExecutionId) {
        Date now = new Date();

        ShopFormExecution shopFormExecution = shopFormExecutionMapper.get(shopFormExecutionId);
        ValueCheckUtils.notNull(shopFormExecution, "店铺表单执行为空");

        List<ShopFormTextItemResponse> inList = new ArrayList<>();
        ShopFormTextItemResponse inRes = null;

        List<ShopFormTextItem> list = shopFormTextItemMapper.getList(shopFormExecutionId, DateUtils.toDateString(now));
        if (list.isEmpty()) {
            Integer formId = shopFormExecution.getFormId();
            List<FormItem> formItemList = formItemMapper.getList(formId);
            if (!formItemList.isEmpty()) {
                ShopFormTextItem shopFormTextItem = null;

                FormItem formItem = null;
                for (int i = 0; i < formItemList.size(); i++) {
                    formItem = formItemList.get(i);

                    shopFormTextItem = new ShopFormTextItem();

                    shopFormTextItem.setShopFormExecutionId(shopFormExecutionId);
                    shopFormTextItem.setFormItemId(formItem.getFormItemId());
                    shopFormTextItem.setFormItemName(formItem.getFormItemName());
                    shopFormTextItem.setOrderNum(formItem.getOrderNum());
                    shopFormTextItem.setExecuteStatus(ExecuteStatusEnum.NOT_EXECUTE.getCode());
                    shopFormTextItem.setActionItem(formItem.getActionItem());
                    shopFormTextItem.setCreateTime(now);

                    shopFormTextItemMapper.add(shopFormTextItem);

                    inRes = new ShopFormTextItemResponse();

                    BeanUtils.copyProperties(shopFormTextItem, inRes);

                    inList.add(inRes);
                }
            }
        } else {
            for (int i = 0; i < list.size(); i ++) {
                inRes = new ShopFormTextItemResponse();
                BeanUtils.copyProperties(list.get(i), inRes);
                inList.add(inRes);
            }
        }

        ShopFormTextItemListForShopownerResponse res = new ShopFormTextItemListForShopownerResponse();

        res.setFormName(shopFormExecution.getFormName());
        res.setReportStatus(shopFormExecution.getReportStatus());
        res.setConfirmStatus(shopFormExecution.getConfirmStatus());
        res.setComment(shopFormExecution.getComment());
        res.setShopFormTextItemList(inList);

        return res;
    }

    @Transactional
    public ShopFormTextItemListResponse getShopFormTextItemList(final Long shopFormExecutionId) {
        Date now = new Date();

        ShopFormExecution shopFormExecution = shopFormExecutionMapper.get(shopFormExecutionId);
        ValueCheckUtils.notNull(shopFormExecution, "店铺表单执行为空");

        List<ShopFormTextItemResponse> inList = new ArrayList<>();
        ShopFormTextItemResponse inRes = null;

        List<ShopFormTextItem> list = shopFormTextItemMapper.getList(shopFormExecutionId, DateUtils.toDateString(now));
        if (list.isEmpty()) {
            Integer formId = shopFormExecution.getFormId();
            List<FormItem> formItemList = formItemMapper.getList(formId);
            if (!formItemList.isEmpty()) {
                ShopFormTextItem shopFormTextItem = null;

                FormItem formItem = null;
                for (int i = 0; i < formItemList.size(); i++) {
                    formItem = formItemList.get(i);

                    shopFormTextItem = new ShopFormTextItem();

                    shopFormTextItem.setShopFormExecutionId(shopFormExecutionId);
                    shopFormTextItem.setFormItemId(formItem.getFormItemId());
                    shopFormTextItem.setFormItemName(formItem.getFormItemName());
                    shopFormTextItem.setOrderNum(formItem.getOrderNum());
                    shopFormTextItem.setExecuteStatus(ExecuteStatusEnum.NOT_EXECUTE.getCode());
                    shopFormTextItem.setActionItem(formItem.getActionItem());
                    shopFormTextItem.setCreateTime(now);

                    shopFormTextItemMapper.add(shopFormTextItem);

                    inRes = new ShopFormTextItemResponse();

                    BeanUtils.copyProperties(shopFormTextItem, inRes);

                    inList.add(inRes);
                }
            }
        } else {
            for (int i = 0; i < list.size(); i ++) {
                inRes = new ShopFormTextItemResponse();
                BeanUtils.copyProperties(list.get(i), inRes);
                inList.add(inRes);
            }
        }

        ShopFormTextItemListResponse res = new ShopFormTextItemListResponse();

        res.setShopId(shopFormExecution.getShopId());
        res.setFormName(shopFormExecution.getFormName());
        res.setConfirmStatus(shopFormExecution.getConfirmStatus());
        res.setComment(shopFormExecution.getComment());
        res.setShopFormTextItemList(inList);

        return res;
    }

    public void executeShopFormTextItem(final Long shopFormTextItemId) {
        shopFormTextItemMapper.updateExecuteStatus(ExecuteStatusEnum.HAS_EXECUTE.getCode(), new Date(), shopFormTextItemId);
    }

}
