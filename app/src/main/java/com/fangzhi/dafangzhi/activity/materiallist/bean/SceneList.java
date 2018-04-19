package com.fangzhi.dafangzhi.activity.materiallist.bean;



import java.util.List;

/**
 * Created by smacr on 2017/4/5.
 */

public class SceneList {
    private String scene_img;

    private String design_img;

    private String scene_name;

    private String hot_name;

    private int scene_id;

    private List<TypeList> typeList ;

    private String code_desc;

    private int hot_id;

    boolean isture;

    public void setScene_img(String scene_img){
        this.scene_img = scene_img;
    }
    public String getScene_img(){
        return this.scene_img;
    }
    public void setDesign_img(String design_img){
        this.design_img = design_img;
    }
    public String getDesign_img(){
        return this.design_img;
    }
    public void setScene_name(String scene_name){
        this.scene_name = scene_name;
    }
    public String getScene_name(){
        return this.scene_name;
    }
    public void setHot_name(String hot_name){
        this.hot_name = hot_name;
    }
    public String getHot_name(){
        return this.hot_name;
    }
    public void setScene_id(int scene_id){
        this.scene_id = scene_id;
    }
    public int getScene_id(){
        return this.scene_id;
    }
    public void setTypeList(List<TypeList> typeList){
        this.typeList = typeList;
    }
    public List<TypeList> getTypeList(){
        return this.typeList;
    }
    public void setCode_desc(String code_desc){
        this.code_desc = code_desc;
    }
    public String getCode_desc(){
        return this.code_desc;
    }
    public void setHot_id(int hot_id){
        this.hot_id = hot_id;
    }
    public int getHot_id(){
        return this.hot_id;
    }

    public boolean isture() {
        return isture;
    }

    public void setIsture(boolean isture) {
        this.isture = isture;
    }
}
