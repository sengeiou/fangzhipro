package com.fangzhi.dafangzhi.fragment.collect.bean;

/**
 * Created by smacr on 2017/4/8.
 */

public class GoodsList {
    private int id;

    private double basic_price;

    private int collect_id;

    private int collect_type;

    private String goods_icon;

    private String goods_name;

    private String introduction;

    boolean isselect=false;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setBasic_price(double basic_price){
        this.basic_price = basic_price;
    }
    public double getBasic_price(){
        return this.basic_price;
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
    public void setGoods_icon(String goods_icon){
        this.goods_icon = goods_icon;
    }
    public String getGoods_icon(){
        return this.goods_icon;
    }
    public void setGoods_name(String goods_name){
        this.goods_name = goods_name;
    }
    public String getGoods_name(){
        return this.goods_name;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public String getIntroduction(){
        return this.introduction;
    }

    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }
}
