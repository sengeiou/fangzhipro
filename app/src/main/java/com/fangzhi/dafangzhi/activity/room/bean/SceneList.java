package com.fangzhi.dafangzhi.activity.room.bean;

/**
 * Created by smacr on 2017/4/5.
 */

public class SceneList {
    private String scene_img;

    private String scene_name;

    private String is_collected;

    private int scene_id;

    private String hot_id;

    boolean isSelected;

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
    public void setIs_collected(String is_collected){
        this.is_collected = is_collected;
    }
    public String getIs_collected(){
        return this.is_collected;
    }
    public void setScene_id(int scene_id){
        this.scene_id = scene_id;
    }
    public int getScene_id(){
        return this.scene_id;
    }
    public void setHot_id(String hot_id){
        this.hot_id = hot_id;
    }
    public String getHot_id(){
        return this.hot_id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
