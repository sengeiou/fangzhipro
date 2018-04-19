package com.fangzhi.dafangzhi.activity.room.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smacr on 2017/3/31.
 */

public class SceneInfo {

    private String scene_img;

    private String scene_name;

    private List<DefaultPartList> defaultPartList =new ArrayList<>();;

    private String scene_id;

    private String hl_img;

    public void setScene_img(String scene_img){
        this.scene_img = scene_img;
    }
    public String getScene_img(){
        return this.scene_img;
    }
    public void setScene_name(String scene_name){
        this.scene_name = scene_name;
    }
    public String getScene_name(){
        return this.scene_name;
    }
    public void setDefaultPartList(List<DefaultPartList> defaultPartList){
        this.defaultPartList = defaultPartList;
    }
    public List<DefaultPartList> getDefaultPartList(){
        return this.defaultPartList;
    }
    public void setScene_id(String scene_id){
        this.scene_id = scene_id;
    }
    public String getScene_id(){
        return this.scene_id;
    }
    public void setHl_img(String hl_img){
        this.hl_img = hl_img;
    }
    public String getHl_img(){
        return this.hl_img;
    }

}
