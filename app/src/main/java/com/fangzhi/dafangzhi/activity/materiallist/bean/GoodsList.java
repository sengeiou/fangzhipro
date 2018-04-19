package com.fangzhi.dafangzhi.activity.materiallist.bean;

/**
 * Created by smacr on 2017/4/5.
 */

public class GoodsList {
    private String basic_price;

    private String goods_id;

    private String goods_icon;

    private int scene_id;

    private String goods_name;

    private String goods_code;

    private int type;

    private double part_amount;

    private String goods_img;

    private String introduction;

    public void setBasic_price(String basic_price){
        this.basic_price = basic_price;
    }
    public String getBasic_price(){
        return this.basic_price;
    }
    public void setGoods_icon(String goods_icon){
        this.goods_icon = goods_icon;
    }
    public String getGoods_icon(){
        return this.goods_icon;
    }
    public void setScene_id(int scene_id){
        this.scene_id = scene_id;
    }
    public int getScene_id(){
        return this.scene_id;
    }
    public void setGoods_name(String goods_name){
        this.goods_name = goods_name;
    }
    public String getGoods_name(){
        return this.goods_name;
    }
    public void setGoods_code(String goods_code){
        this.goods_code = goods_code;
    }
    public String getGoods_code(){
        return this.goods_code;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setPart_amount(double part_amount){
        this.part_amount = part_amount;
    }
    public double getPart_amount(){
        return this.part_amount;
    }
    public void setGoods_img(String goods_img){
        this.goods_img = goods_img;
    }
    public String getGoods_img(){
        return this.goods_img;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public String getIntroduction(){
        return this.introduction;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}
