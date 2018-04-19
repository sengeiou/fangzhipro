package com.fangzhi.dafangzhi.activity.login.bean;

import com.fangzhi.dafangzhi.base.BaseBean;

/**
 * Created by smacr on 2017/3/18.
 */

public class LoginBean extends BaseBean {
    String userID;
    String token;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
