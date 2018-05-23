package com.weimi.formx.michelin.service;

import com.weimi.formx.common.entity.*;
import com.weimi.formx.common.enumeration.ConfirmStatusEnum;
import com.weimi.formx.common.enumeration.ReportStatusEnum;
import com.weimi.formx.common.enumeration.RoleEnum;
import com.weimi.formx.common.mapper.FormMapper;
import com.weimi.formx.common.mapper.ShopExecuteRecordMapper;
import com.weimi.formx.common.mapper.ShopFormExecutionMapper;
import com.weimi.formx.common.mapper.ShopMapper;
import com.weimi.formx.common.response.ResponseCode;
import com.weimi.formx.exception.FormxException;
import com.weimi.formx.michelin.response.ShopFormExecutionResponse;
import com.weimi.formx.michelin.response.ShopFormExecutionListResponse;
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
public class ShopFormExecutionService {

    @Resource
    private UserShopRelationService userShopRelationService;

    @Resource
    private ShopFormExecutionMapper shopFormExecutionMapper;
    @Resource
    private ShopMapper shopMapper;
    @Resource
    private FormMapper formMapper;
    @Resource
    private ShopExecuteRecordMapper shopExecuteRecordMapper;

    @Resource
    private HttpSession httpSession;

    public List<ShopFormExecutionResponse> getShopFormExecutionListForShopowner() {
        User user = (User) httpSession.getAttribute("user");
        ValueCheckUtils.notNull(user, "用户信息获取失败");

        Integer roleId = user.getRoleId();
        if (!RoleEnum.SHOPOWNER.getRoleId().equals(roleId)) {
            throw new FormxException(ResponseCode.DATA_EXCEPTION, "当前用户不是店长");
        }

        Integer shopId = userShopRelationService.getShopOwnerShopId((user.getUserId()));
        ValueCheckUtils.notNull(shopId, "无店铺信息");

        log.info("shopId: {}", shopId);

        List<ShopFormExecutionResponse> resList = new ArrayList<>();
        ShopFormExecutionResponse res = null;

        List<ShopFormExecution> list = getShopFormExecutions(shopId);
        for (int i = 0; i < list.size(); i++) {
            res = new ShopFormExecutionResponse();

            BeanUtils.copyProperties(list.get(i), res);

            resList.add(res);
        }

        return resList;
    }

    public ShopFormExecutionListResponse getShopFormExecutionList(final Integer shopId) {
        Shop shop = shopMapper.get(shopId);
        ValueCheckUtils.notNull(shopId, "店铺不存在");

        List<ShopFormExecutionResponse> inResList = new ArrayList<>();
        ShopFormExecutionResponse inRes = null;

        List<ShopFormExecution> list = getShopFormExecutions(shopId);
        for (int i = 0; i < list.size(); i++) {
            inRes = new ShopFormExecutionResponse();

            BeanUtils.copyProperties(list.get(i), inRes);

            inResList.add(inRes);
        }

        ShopFormExecutionListResponse res = new ShopFormExecutionListResponse();

        res.setShopName(shop.getShopName());
        res.setShopFormExecutionList(inResList);

        return res;
    }

    @Transactional
    public List<ShopFormExecution> getShopFormExecutions(Integer shopId) {
        Date now = new Date();
        List<ShopFormExecution> list = shopFormExecutionMapper.getList(shopId, DateUtils.toDateString(now));
        if (list.isEmpty()) {
            ShopFormExecution shopFormExecution = null;

            List<Form> formList = formMapper.getList();
            Form form = null;
            for (int i = 0; i < formList.size(); i++) {
                form = formList.get(i);

                shopFormExecution = new ShopFormExecution();

                shopFormExecution.setShopId(shopId);
                shopFormExecution.setFormId(form.getFormId());
                shopFormExecution.setFormName(form.getFormName());
                shopFormExecution.setFormType(form.getFormType());
                shopFormExecution.setReportStatus(ReportStatusEnum.NOT_REPORT.getCode());
                shopFormExecution.setConfirmStatus(ConfirmStatusEnum.NOT_CONFIRM.getCode());
                shopFormExecution.setOrderNum(form.getOrderNum());
                shopFormExecution.setCreateTime(now);

                shopFormExecutionMapper.add(shopFormExecution);

                list.add(shopFormExecution);
            }
        }

        return list;
    }

    @Transactional
    public void reportShopFormExecution(final Long shopFormExecutionId) {
        Date now = new Date();

        shopFormExecutionMapper.updateReportStatus(ReportStatusEnum.HAS_REPORT.getCode(), now, shopFormExecutionId, DateUtils.toDateString(now));

        ShopFormExecution shopFormExecution = shopFormExecutionMapper.get(shopFormExecutionId);

        Integer shopId = shopFormExecution.getShopId();
        Shop shop = shopMapper.get(shopId);
        ValueCheckUtils.notNull(shop, "店铺不存在");

        boolean complete = true;
        List<ShopFormExecution> shopFormExecutionList = shopFormExecutionMapper.getList(shopId, DateUtils.toDateString(now));
        for (int i = 0; i < shopFormExecutionList.size(); i++) {
            if (shopFormExecutionList.get(i).getReportStatus().equals(ReportStatusEnum.NOT_REPORT.getCode())) {
                complete = false;
                break;
            }
        }

        if (complete) {
            ShopExecuteRecord shopExecuteRecord = new ShopExecuteRecord();

            shopExecuteRecord.setShopId(shopId);
            shopExecuteRecord.setShopName(shop.getShopName());
            shopExecuteRecord.setCompleteTime(now);
            shopExecuteRecord.setConfirmStatus(ConfirmStatusEnum.NOT_CONFIRM.getCode());

            shopExecuteRecordMapper.add(shopExecuteRecord);
        }
    }

    @Transactional
    public void confirmShopFormExecution(final Long shopFormExecutionId) {
        Date now = new Date();

        shopFormExecutionMapper.updateConfirmStatus(ConfirmStatusEnum.HAS_CONFIRM.getCode(), now, shopFormExecutionId, DateUtils.toDateString(now));

        ShopFormExecution shopFormExecution = shopFormExecutionMapper.get(shopFormExecutionId);

        Integer shopId = shopFormExecution.getShopId();

        boolean confirm = true;

        List<ShopFormExecution> shopFormExecutionList = shopFormExecutionMapper.getList(shopId, DateUtils.toDateString(now));
        for (int i = 0; i < shopFormExecutionList.size(); i++) {
            if (shopFormExecutionList.get(i).getConfirmStatus().equals(ConfirmStatusEnum.NOT_CONFIRM.getCode())) {
                confirm = false;
                break;
            }
        }

        if (confirm) {
            shopExecuteRecordMapper.updateConfirmStatus(ConfirmStatusEnum.HAS_CONFIRM.getCode(), now, shopId, DateUtils.toDateString(now));
        }
    }

    public void comment(final Long shopFormExecutionId, final String comment) {
        shopFormExecutionMapper.updateComment(comment, shopFormExecutionId, DateUtils.toDateString(new Date()));
    }

}
