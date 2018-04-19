package com.fangzhi.dafangzhi.activity.materiallist.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smacr on 2017/4/5.
 */

public class TypeList {
    private List<GoodsList> goodsList =new ArrayList<>();

    private String type_name;

    private int scene_id;

    private String total_price;

    private int type;

    public void setGoodsList(List<GoodsList> goodsList){
        this.goodsList = goodsList;
    }
    public List<GoodsList> getGoodsList(){
        return this.goodsList;
    }
    public void setType_name(String type_name){
        this.type_name = type_name;
    }
    public String getType_name(){
        return this.type_name;
    }
    public void setScene_id(int scene_id){
        this.scene_id = scene_id;
    }
    public int getScene_id(){
        return this.scene_id;
    }
    public void setTatal_price(String tatal_price){
        this.total_price = tatal_price;
    }
    public String getTatal_price(){
        return this.total_price;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
}
