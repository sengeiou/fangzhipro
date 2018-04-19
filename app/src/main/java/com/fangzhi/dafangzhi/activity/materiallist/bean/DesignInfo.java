package com.fangzhi.dafangzhi.activity.materiallist.bean;


import java.util.List;

/**
 * Created by smacr on 2017/4/5.
 */

public class DesignInfo {
    private String name;

    private String img;

    private List<SceneList> sceneList ;

    private String house_name;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
    public void setSceneList(List<SceneList> sceneList){
        this.sceneList = sceneList;
    }
    public List<SceneList> getSceneList(){
        return this.sceneList;
    }
    public void setHouse_name(String house_name){
        this.house_name = house_name;
    }
    public String getHouse_name(){
        return this.house_name;
    }

}
