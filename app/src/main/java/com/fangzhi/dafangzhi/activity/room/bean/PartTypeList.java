package com.fangzhi.dafangzhi.activity.room.bean;

import java.util.List;

/**
 * Created by smacr on 2017/3/31.
 */

public class PartTypeList {
    private int order_num;

    private String type_name;

    private int page_no;

    private int type;

    private List<PartList> partList ;

    private int type_id;

    private int parent_id;

    boolean isture;

    public void setOrder_num(int order_num){
        this.order_num = order_num;
    }
    public int getOrder_num(){
        return this.order_num;
    }
    public void setType_name(String type_name){
        this.type_name = type_name;
    }
    public String getType_name(){
        return this.type_name;
    }
    public void setPage_no(int page_no){
        this.page_no = page_no;
    }
    public int getPage_no(){
        return this.page_no;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setPartList(List<PartList> partList){
        this.partList = partList;
    }
    public List<PartList> getPartList(){
        return this.partList;
    }
    public void setType_id(int type_id){
        this.type_id = type_id;
    }
    public int getType_id(){
        return this.type_id;
    }
    public void setParent_id(int parent_id){
        this.parent_id = parent_id;
    }
    public int getParent_id(){
        return this.parent_id;
    }

    public boolean isture() {
        return isture;
    }

    public void setIsture(boolean isture) {
        this.isture = isture;
    }
}
