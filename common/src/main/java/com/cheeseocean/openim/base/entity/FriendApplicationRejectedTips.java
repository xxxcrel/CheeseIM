package com.cheeseocean.openim.base.entity;

/**
 * @author xxxcrel
 * Created on 2022/5/22
 */
public class FriendApplicationRejectedTips {
    private FromToUserID fromToUserID;
    private String handleMsg;

    public FromToUserID getFromToUserID() {
        return fromToUserID;
    }

    public void setFromToUserID(FromToUserID fromToUserID) {
        this.fromToUserID = fromToUserID;
    }

    public String getHandleMsg() {
        return handleMsg;
    }

    public void setHandleMsg(String handleMsg) {
        this.handleMsg = handleMsg;
    }
}
