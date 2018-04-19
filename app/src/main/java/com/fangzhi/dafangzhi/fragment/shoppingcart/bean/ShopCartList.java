package com.fangzhi.dafangzhi.fragment.shoppingcart.bean;

/**
 * Created by smacr on 2017/4/10.
 */

public class ShopCartList {
    private int id;

    private String standard_name;

    private double price;

    private String color_id;

    private String standard_id;

    private String color_name;

    private String goods_icon;

    private String uniqueInfo;

    private String goods_name;

    private int quantity;

    private String size_id;

    private String size_name;

    boolean select=false;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setStandard_name(String standard_name){
        this.standard_name = standard_name;
    }
    public String getStandard_name(){
        return this.standard_name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return this.price;
    }
    public void setColor_id(String color_id){
        this.color_id = color_id;
    }
    public String getColor_id(){
        return this.color_id;
    }
    public void setStandard_id(String standard_id){
        this.standard_id = standard_id;
    }
    public String getStandard_id(){
        return this.standard_id;
    }
    public void setColor_name(String color_name){
        this.color_name = color_name;
    }
    public String getColor_name(){
        return this.color_name;
    }
    public void setGoods_icon(String goods_icon){
        this.goods_icon = goods_icon;
    }
    public String getGoods_icon(){
        return this.goods_icon;
    }
    public void setUniqueInfo(String uniqueInfo){
        this.uniqueInfo = uniqueInfo;
    }
    public String getUniqueInfo(){
        return this.uniqueInfo;
    }
    public void setGoods_name(String goods_name){
        this.goods_name = goods_name;
    }
    public String getGoods_name(){
        return this.goods_name;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void setSize_id(String size_id){
        this.size_id = size_id;
    }
    public String getSize_id(){
        return this.size_id;
    }
    public void setSize_name(String size_name){
        this.size_name = size_name;
    }
    public String getSize_name(){
        return this.size_name;
    }
}
