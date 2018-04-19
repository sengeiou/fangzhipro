package com.fangzhi.dafangzhi.activity.room.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smacr on 2017/3/31.
 */

public class RoomBean {
    private SceneInfo sceneInfo;

    private String error_code;

    private List<TypeList> typeList =new ArrayList<>();;

    private String msg;

    public void setSceneInfo(SceneInfo sceneInfo){
        this.sceneInfo = sceneInfo;
    }
    public SceneInfo getSceneInfo(){
        return this.sceneInfo;
    }
    public void setError_code(String error_code){
        this.error_code = error_code;
    }
    public String getError_code(){
        return this.error_code;
    }
    public void setTypeList(List<TypeList> typeList){
        this.typeList = typeList;
    }
    public List<TypeList> getTypeList(){
        return this.typeList;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }

}
