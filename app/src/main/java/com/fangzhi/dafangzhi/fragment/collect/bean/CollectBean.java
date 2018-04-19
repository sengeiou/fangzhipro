package com.fangzhi.dafangzhi.fragment.collect.bean;


import java.util.List;

/**
 * Created by smacr on 2017/4/8.
 */

public class CollectBean {
    private List<GoodsList> goodsList ;

    private List<SceneList> sceneList ;

    private String error_code;

    private String msg;

    public void setGoodsList(List<GoodsList> goodsList){
        this.goodsList = goodsList;
    }
    public List<GoodsList> getGoodsList(){
        return this.goodsList;
    }
    public void setSceneList(List<SceneList> sceneList){
        this.sceneList = sceneList;
    }
    public List<SceneList> getSceneList(){
        return this.sceneList;
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
