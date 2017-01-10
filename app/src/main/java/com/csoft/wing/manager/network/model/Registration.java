
package com.csoft.wing.manager.network.model;


import com.google.gson.annotations.SerializedName;

public class Registration {

    @SerializedName("chatapp_id")
    private String mChatAppId;
    @SerializedName("code")
    private String mCode;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("nickname")
    private String mNickname;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("user_type")
    private String mUserType;

    public String getChatappId() {
        return mChatAppId;
    }

    public void setChatappId(String chatapp_id) {
        mChatAppId = chatapp_id;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getNickname() {
        return mNickname;
    }

    public void setNickname(String nickname) {
        mNickname = nickname;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String user_id) {
        mUserId = user_id;
    }

    public String getUserType() {
        return mUserType;
    }

    public void setUserType(String user_type) {
        mUserType = user_type;
    }

}
