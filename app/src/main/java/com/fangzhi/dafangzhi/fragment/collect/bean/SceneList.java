package com.fangzhi.dafangzhi.fragment.collect.bean;

/**
 * Created by smacr on 2017/4/8.
 */

public class SceneList {
    private int id;

    private String scene_img;

    private String city_name;

    private String province_name;

    private String design_img;

    private int collect_id;

    private int collect_type;

    private String code_desc;

    private String county_name;

    private String design_name;

    boolean isselect=false;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setScene_img(String scene_img){
        this.scene_img = scene_img;
    }
    public String getScene_img(){
        return this.scene_img;
    }
    public void setCity_name(String city_name){
        this.city_name = city_name;
    }
    public String getCity_name(){
        return this.city_name;
    }
    public void setProvince_name(String province_name){
        this.province_name = province_name;
    }
    public String getProvince_name(){
        return this.province_name;
    }
    public void setDesign_img(String design_img){
        this.design_img = design_img;
    }
    public String getDesign_img(){
        return this.design_img;
    }
    public void setCollect_id(int collect_id){
        this.collect_id = collect_id;
    }
    public int getCollect_id(){
        return this.collect_id;
    }
    public void setCollect_type(int collect_type){
        this.collect_type = collect_type;
    }
    public int getCollect_type(){
        return this.collect_type;
    }
    public void setCode_desc(String code_desc){
        this.code_desc = code_desc;
    }
    public String getCode_desc(){
        return this.code_desc;
    }
    public void setCounty_name(String county_name){
        this.county_name = county_name;
    }
    public String getCounty_name(){
        return this.county_name;
    }
    public void setDesign_name(String design_name){
        this.design_name = design_name;
    }
    public String getDesign_name(){
        return this.design_name;
    }

    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }
}
