package com.fangzhi.dafangzhi.activity.room.bean;

/**
 * Created by smacr on 2017/3/31.
 */

public class DefaultPartList {
    private String part_id;

    private String part_name;

    private String part_img;

    private int order_num;

    private int goods_id;

    private String style_code;

    private int page_no;

    private String part_code;

    private int type_id;

    private String part_img_short;

    public String getPart_id() {
        return part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    public void setPart_name(String part_name){
        this.part_name = part_name;
    }
    public String getPart_name(){
        return this.part_name;
    }
    public void setPart_img(String part_img){
        this.part_img = part_img;
    }
    public String getPart_img(){
        return this.part_img;
    }
    public void setOrder_num(int order_num){
        this.order_num = order_num;
    }
    public int getOrder_num(){
        return this.order_num;
    }
    public void setGoods_id(int goods_id){
        this.goods_id = goods_id;
    }
    public int getGoods_id(){
        return this.goods_id;
    }
    public void setStyle_code(String style_code){
        this.style_code = style_code;
    }
    public String getStyle_code(){
        return this.style_code;
    }
    public void setPage_no(int page_no){
        this.page_no = page_no;
    }
    public int getPage_no(){
        return this.page_no;
    }
    public void setPart_code(String part_code){
        this.part_code = part_code;
    }
    public String getPart_code(){
        return this.part_code;
    }
    public void setType_id(int type_id){
        this.type_id = type_id;
    }
    public int getType_id(){
        return this.type_id;
    }
    public void setPart_img_short(String part_img_short){
        this.part_img_short = part_img_short;
    }
    public String getPart_img_short(){
        return this.part_img_short;
    }
}
