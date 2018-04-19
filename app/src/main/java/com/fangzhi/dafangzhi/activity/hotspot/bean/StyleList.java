package com.fangzhi.dafangzhi.activity.hotspot.bean;

import java.util.List;

/**
 * Created by smacr on 2017/3/28.
 */

public class StyleList {
    private List<DesignList> designList ;

    private String code_id;

    private String code_desc;

    private boolean isture=false;

    public void setDesignList(List<DesignList> designList){
        this.designList = designList;
    }
    public List<DesignList> getDesignList(){
        return this.designList;
    }
    public void setCode_id(String code_id){
        this.code_id = code_id;
    }
    public String getCode_id(){
        return this.code_id;
    }
    public void setCode_desc(String code_desc){
        this.code_desc = code_desc;
    }
    public String getCode_desc(){
        return this.code_desc;
    }

    public boolean isture() {
        return isture;
    }

    public void setIsture(boolean isture) {
        this.isture = isture;
    }
}
