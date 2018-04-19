package com.fangzhi.dafangzhi.network;

/**
 * Created by zhangyu on 2016/6/15.
 */
public class ApiUrl {

    /**
     * 正式
     */
    //public static final String BASE_URL = "http://51fangz.com:8050/";
    /**
     * 测试
     */
    public static final String BASE_URL = "http://120.76.209.107:8060/";

    /**
     * 验证码发送
     */
    public static final String FzAction_SENDSMS = "fzweb/fzweb/FzAction/sendSms.json";
    /**
     * 验证验证码是否有效
     */
    public static final String FzAction_QUERYVERCODE = "fzweb/fzweb/FzAction/queryVerCode.json";
    /**
     * 注册
     */
    public static final String FzAction_REGISTER = "fzweb/fzweb/FzAction/register.json";
    /**
     * 忘记密码
     */
    public static final String FzAction_MODIFY = "fzweb/fzweb/FzAction/modify.json";
    /**
     * 登录
     */
    public static final String FzAction_LOGIN= "fzweb/fzweb/FzAction/login.json";
    /**
     * 获取城市列表
     */
    public static final String FzAction_GETAREA= "fzweb/fzweb/FzAction/getArea.json";

    /**
     * 首页列表
     */
    public static final String FzAction_GETHOMEPAGE= "fzweb/fzweb/FzAction/getHomePage.json";

    /**
     * 搜索楼盘信息
     */
    public static final String FzAction_SEARCHPREMISE= "fzweb/fzweb/FzAction/searchPremise.json";

    /**
     * 获取指定区县楼盘信息
     */
    public static final String FzAction_GETCOUNTYPRIMISE= "fzweb/fzweb/FzAction/getCountyPrimise.json";

    /**
     * 搜索楼盘信息
     */
    public static final String FzAction_getHouseType= "fzweb/fzweb/FzAction/getHouseType.json";

    /**
     * 获取设计图
     */
    public static final String FzAction_getHouseDesign= "fzweb/fzweb/FzAction/getHouseDesign.json";

    /**
     * 获取热点的场景
     */
    public static final String FzAction_getScene= "fzweb/fzweb/FzAction/getScene.json";

    /**
     * 获取设计图的材料清单
     */
    public static final String FzAction_getMaterialList= "fzweb/fzweb/FzAction/getMaterialList.json";

    /**
     * 查看该设计图其他空间场景
     */
    public static final String FzAction_getOtherScene= "fzweb/fzweb/FzAction/getOtherScene.json";

    /**
     * 收藏设计方案
     */
    public static final String FzAction_collectDesign= "fzweb/fzweb/FzAction/collectDesign.json";

    /**
     * 传递图片地址id 获取高清大图
     */
    public static final String MERGEPICTURE = "http://120.76.212.114:8040/tmaven/part/mergePicture.json?";

    /**
     *获取设计图的材料清单
     */
    public static final String GETMATERIALLIST = "fzweb/fzweb/FzAction/getMaterialList.json";

    /**
     *获取收藏列表
     */
    public static final String GETCOLLECTLIST = "fzweb/fzweb/GoodsAction/getCollectList.json";

    /**
     *获取购物车列表
     */
    public static final String GETSHOPCARTLIST = "fzweb/fzweb/GoodsAction/getShopCartList.json";
 }
