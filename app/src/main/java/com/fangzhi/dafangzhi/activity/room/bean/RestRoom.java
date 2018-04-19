package com.fangzhi.dafangzhi.activity.room.bean;

import java.util.List;

/**
 * Created by smacr on 2017/4/5.
 */

public class RestRoom {
    private List<SceneList> sceneList ;

    private String error_code;

    private String msg;

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
