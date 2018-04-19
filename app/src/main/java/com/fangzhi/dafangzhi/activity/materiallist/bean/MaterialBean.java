package com.fangzhi.dafangzhi.activity.materiallist.bean;

/**
 * Created by smacr on 2017/4/5.
 */

public class MaterialBean {
    private DesignInfo designInfo;

    private String error_code;

    private String msg;

    public void setDesignInfo(DesignInfo designInfo){
        this.designInfo = designInfo;
    }
    public DesignInfo getDesignInfo(){
        return this.designInfo;
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
