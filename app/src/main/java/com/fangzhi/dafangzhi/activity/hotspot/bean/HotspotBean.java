package com.fangzhi.dafangzhi.activity.hotspot.bean;

import java.util.List;

/**
 * Created by smacr on 2017/3/28.
 */

public class HotspotBean {
    private List<StyleList> styleList ;

    private String error_code;

    private String msg;

    public void setStyleList(List<StyleList> styleList){
        this.styleList = styleList;
    }
    public List<StyleList> getStyleList(){
        return this.styleList;
    }
    public void setError_code(String error_code){
        this.error_code = error_code;
    }
    public String getError_code(){
        return this.error_code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
}
