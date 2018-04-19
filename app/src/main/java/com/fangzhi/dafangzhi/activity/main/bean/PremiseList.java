package com.fangzhi.dafangzhi.activity.main.bean;

/**
 * Created by smacr on 2017/3/27.
 */

public class PremiseList {
    private int id;

    private String pre_cname;

    private String pre_ename;

    private int count;

    private String pre_img;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setPre_cname(String pre_cname){
        this.pre_cname = pre_cname;
    }
    public String getPre_cname(){
        return this.pre_cname;
    }
    public void setPre_ename(String pre_ename){
        this.pre_ename = pre_ename;
    }
    public String getPre_ename(){
        return this.pre_ename;
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setPre_img(String pre_img){
        this.pre_img = pre_img;
    }
    public String getPre_img(){
        return this.pre_img;
    }

}
