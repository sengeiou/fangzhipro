package com.fangzhi.dafangzhi.fragment.shoppingcart.bean;

import java.util.List;

/**
 * Created by smacr on 2017/4/10.
 */

public class ShopBean {
    private List<ShopCartList> shopCartList ;

    private String error_code;

    private String msg;

    public void setShopCartList(List<ShopCartList> shopCartList){
        this.shopCartList = shopCartList;
    }
    public List<ShopCartList> getShopCartList(){
        return this.shopCartList;
    }
    public void setError_code(String error_code){
        this.error_code = error_code;
    }
    public String getError_code(){
        return this.error_code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
}
