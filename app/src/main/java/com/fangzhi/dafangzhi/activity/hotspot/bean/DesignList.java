package com.fangzhi.dafangzhi.activity.hotspot.bean;

import java.util.List;

/**
 * Created by smacr on 2017/3/28.
 */

public class DesignList {
    private List<HotList> hotList ;

    private String design_id;

    private String img;

    private String style_code;

    private String house_name;

    public void setHotList(List<HotList> hotList){
        this.hotList = hotList;
    }
    public List<HotList> getHotList(){
        return this.hotList;
    }
    public void setDesign_id(String design_id){
        this.design_id = design_id;
    }
    public String getDesign_id(){
        return this.design_id;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
    public void setStyle_code(String style_code){
        this.style_code = style_code;
    }
    public String getStyle_code(){
        return this.style_code;
    }
    public void setHouse_name(String house_name){
        this.house_name = house_name;
    }
    public String getHouse_name(){
        return this.house_name;
    }

}
