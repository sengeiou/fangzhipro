package com.fangzhi.dafangzhi.activity.main.bean;

import com.fangzhi.dafangzhi.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smacr on 2017/3/17.
 */

public class MainBean extends BaseBean {
    private List<PremiseList> premiseList=new ArrayList<>(); ;

    private int shopCart;

    private List<CountyList> countyList ;

    private int collection;


    public void setPremiseList(List<PremiseList> premiseList){
        this.premiseList = premiseList;
    }
    public List<PremiseList> getPremiseList(){
        return this.premiseList;
    }
    public void setShopCart(int shopCart){
        this.shopCart = shopCart;
    }
    public int getShopCart(){
        return this.shopCart;
    }
    public void setCountyList(List<CountyList> countyList){
        this.countyList = countyList;
    }
    public List<CountyList> getCountyList(){
        return this.countyList;
    }
    public void setCollection(int collection){
        this.collection = collection;
    }
    public int getCollection(){
        return this.collection;
    }
}
