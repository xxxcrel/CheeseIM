package com.cheeseocean.im.postman.service;

import com.cheeseocean.im.postoffice.relay.api.OnlineMessageRelayService;
import com.cheeseocean.im.postoffice.relay.api.OnlinePushMsgReq;
import com.cheeseocean.im.postoffice.relay.api.OnlinePushMsgResp;
import com.cheeseocean.im.postoffice.relay.api.SingleMsgToUser;
import com.cheeseocean.im.postman.api.PushMsgReq;
import com.cheeseocean.im.postman.api.PushMsgResp;
import com.cheeseocean.im.postman.api.PushService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService
public class PushServiceImpl implements PushService {

    @DubboReference(check = false)
    private OnlineMessageRelayService onlineRelayService;

    @Override
    public PushMsgResp pushMsg(PushMsgReq pushMsgReq) {
        List<SingleMsgToUser> wsResult = new ArrayList<>();
        OnlinePushMsgResp pushMsgResp = onlineRelayService.onlinePushMsg(OnlinePushMsgReq.newBuilder()
                .operationID(pushMsgReq.getOperationID())
                .msgData(pushMsgReq.getMsgData())
                .pushToUserID(pushMsgReq.getPushToUserID())
                .build());
        return PushMsgResp.newBuilder().resultCode(0).build();
    }
}
