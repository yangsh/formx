package com.weimi.formx.michelin.service;

import com.weimi.formx.common.entity.ShopExecuteRecord;
import com.weimi.formx.common.entity.User;
import com.weimi.formx.common.entity.UserShopRelation;
import com.weimi.formx.common.mapper.ShopExecuteRecordMapper;
import com.weimi.formx.common.mapper.ShopFormExecutionMapper;
import com.weimi.formx.common.mapper.UserShopRelationMapper;
import com.weimi.formx.michelin.response.CompleteShopListForAdviserResponse;
import com.weimi.formx.michelin.response.CompleteShopListForShopkeeperResponse;
import com.weimi.formx.michelin.response.NotCompleteShopListResponse;
import com.weimi.formx.michelin.response.ShopDataResponse;
import com.weimi.formx.util.DateUtils;
import com.weimi.formx.util.ValueCheckUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangsh on 2018-05-20
 */
@Service
public class ShopService {

    @Resource
    private UserShopRelationMapper userShopRelationMapper;
    @Resource
    private ShopExecuteRecordMapper shopExecuteRecordMapper;
    @Resource
    private ShopFormExecutionMapper shopFormExecutionMapper;

    @Resource
    private HttpSession httpSession;

    public ShopDataResponse getShopData() {
        User user = (User) httpSession.getAttribute("user");
        ValueCheckUtils.notNull(user, "用户信息获取失败");

        Date now = new Date();
        String completeTime = DateUtils.toDateString(now);

        List<UserShopRelation> userShopRelations =  userShopRelationMapper.getList(user.getUserId());
        Integer total = userShopRelations.size();
        UserShopRelation userShopRelation = null;

        Integer completeCount = 0;
        for (int i = 0; i < total; i++) {
            userShopRelation = userShopRelations.get(i);

            if (shopExecuteRecordMapper.get(userShopRelation.getShopId(), completeTime) != null) {
                completeCount ++;
            }
        }

        ShopDataResponse res = new ShopDataResponse();

        res.setCompleteCount(completeCount);
        res.setNotCompleteCount(total - completeCount);

        return res;
    }

    public List<CompleteShopListForShopkeeperResponse> getCompleteShopListForShopkeeper() {
        User user = (User) httpSession.getAttribute("user");
        ValueCheckUtils.notNull(user, "用户信息获取失败");

        Date now = new Date();
        String completeTime = DateUtils.toDateString(now);

        List<CompleteShopListForShopkeeperResponse> resList = new ArrayList<>();
        CompleteShopListForShopkeeperResponse res = null;
        ShopExecuteRecord shopExecuteRecord = null;

        List<UserShopRelation> userShopRelations = userShopRelationMapper.getList(user.getUserId());
        UserShopRelation userShopRelation = null;
        for (int i = 0; i < userShopRelations.size(); i ++) {
            userShopRelation = userShopRelations.get(i);
            shopExecuteRecord = shopExecuteRecordMapper.get(userShopRelation.getShopId(), completeTime);
            if (shopExecuteRecord != null) {
                res = new CompleteShopListForShopkeeperResponse();

                res.setShopId(shopExecuteRecord.getShopId());
                res.setShopName(shopExecuteRecord.getShopName());
                res.setConfirmStatus(shopExecuteRecord.getConfirmStatus());

                resList.add(res);
            }
        }

        return resList;
    }

    public List<NotCompleteShopListResponse> getNotCompleteShopList() {
        User user = (User) httpSession.getAttribute("user");
        ValueCheckUtils.notNull(user, "用户信息获取失败");

        Date now = new Date();

        List<NotCompleteShopListResponse> resList = new ArrayList<>();
        NotCompleteShopListResponse res = null;

        ShopExecuteRecord shopExecuteRecord = null;

        List<UserShopRelation> userShopRelations = userShopRelationMapper.getList(user.getUserId());
        UserShopRelation userShopRelation = null;

        Integer shopId = null;
        for (int i = 0; i < userShopRelations.size(); i ++) {
            userShopRelation = userShopRelations.get(i);
            shopExecuteRecord = shopExecuteRecordMapper.get(userShopRelation.getShopId(), DateUtils.toDateString(now));
            if (shopExecuteRecord == null) {
                shopId = userShopRelation.getShopId();

                res = new NotCompleteShopListResponse();

                res.setShopId(shopId);
                res.setShopName(userShopRelation.getShopName());
                res.setFormCompleteCount(shopFormExecutionMapper.getFormCompleteCount(shopId, DateUtils.toDateString(now)));


                resList.add(res);
            }
        }

        return resList;
    }

    public List<CompleteShopListForAdviserResponse> getCompleteShopListForAdviser() {
        User user = (User) httpSession.getAttribute("user");
        ValueCheckUtils.notNull(user, "用户信息获取失败");

        Date now = new Date();
        String completeTime = DateUtils.toDateString(now);

        List<CompleteShopListForAdviserResponse> resList = new ArrayList<>();
        CompleteShopListForAdviserResponse res = null;

        ShopExecuteRecord shopExecuteRecord = null;

        List<UserShopRelation> userShopRelations = userShopRelationMapper.getList(user.getUserId());
        UserShopRelation userShopRelation = null;
        for (int i = 0; i < userShopRelations.size(); i ++) {
            userShopRelation = userShopRelations.get(i);
            shopExecuteRecord = shopExecuteRecordMapper.get(userShopRelation.getShopId(), completeTime);
            if (shopExecuteRecord != null) {
                res = new CompleteShopListForAdviserResponse();

                res.setShopId(shopExecuteRecord.getShopId());
                res.setShopName(shopExecuteRecord.getShopName());

                resList.add(res);
            }
        }

        return resList;
    }

}
