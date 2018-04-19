package com.fangzhi.dafangzhi.activity.room.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smacr on 2017/3/31.
 */

public class StairTypeList {
    private List<PartTypeList> partTypeList =new ArrayList<>();;

    private String type_name;

    private int type;

    private int type_id;

    boolean isture;

    public void setPartTypeList(List<PartTypeList> partTypeList){
        this.partTypeList = partTypeList;
    }
    public List<PartTypeList> getPartTypeList(){
        return this.partTypeList;
    }
    public void setType_name(String type_name){
        this.type_name = type_name;
    }
    public String getType_name(){
        return this.type_name;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setType_id(int type_id){
        this.type_id = type_id;
    }
    public int getType_id(){
        return this.type_id;
    }

    public boolean isture() {
        return isture;
    }

    public void setIsture(boolean isture) {
        this.isture = isture;
    }
}
