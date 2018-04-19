package com.fangzhi.dafangzhi.network;


import com.fangzhi.dafangzhi.activity.city.bean.Area;
import com.fangzhi.dafangzhi.activity.hotspot.bean.HotspotBean;
import com.fangzhi.dafangzhi.activity.login.bean.LoginBean;
import com.fangzhi.dafangzhi.activity.main.bean.MainBean;
import com.fangzhi.dafangzhi.activity.main_type.bean.HouseTypes;
import com.fangzhi.dafangzhi.activity.materiallist.bean.MaterialBean;
import com.fangzhi.dafangzhi.activity.room.bean.RestRoom;
import com.fangzhi.dafangzhi.activity.room.bean.RoomBean;
import com.fangzhi.dafangzhi.activity.room.bean.Sceneorderback;
import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.fragment.collect.bean.CollectBean;
import com.fangzhi.dafangzhi.fragment.shoppingcart.bean.ShopBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhangyu on 2016/6/15.
 */
public interface ApiService {

    @GET(ApiUrl.FzAction_SENDSMS)
    Observable<MainBean> updateVersion(@Query("type") String type);

    @GET(ApiUrl.FzAction_SENDSMS)
    Observable<BaseBean> gainverify(@Query("phone") String phone, @Query("key") String MD5);

    @GET(ApiUrl.FzAction_QUERYVERCODE)
    Observable<BaseBean> queryVerCode(@Query("phone") String phone,
                                      @Query("code") String code, @Query("type") String type);

    @GET(ApiUrl.FzAction_REGISTER)
    Observable<BaseBean> FzAction_REGISTER(@Query("phone") String phone,
                                           @Query("password") String password, @Query("key") String MD5);

    @GET(ApiUrl.FzAction_REGISTER)
    Observable<BaseBean> FzAction_weixinREGISTER(@Query("phone") String phone,
                                                 @Query("password") String password,
                                                 @Query("key") String MD5,
                                                 @Query("sex") String sex,
                                                 @Query("openid") String openid,
                                                 @Query("icon") String icon,
                                                 @Query("openid") String nick_name);

    @GET(ApiUrl.FzAction_MODIFY)
    Observable<BaseBean> FzAction_MODIFY(@Query("phone") String phone,
                                         @Query("passWord") String password, @Query("key") String MD5);

    @GET(ApiUrl.FzAction_LOGIN)
    Observable<LoginBean> FzAction_LOGIN(@Query("phone") String phone,
                                         @Query("password") String password);

    @GET(ApiUrl.FzAction_GETAREA)
    Observable<Area> FzAction_GETAREA();

    @GET(ApiUrl.FzAction_GETHOMEPAGE)
    Observable<MainBean> FzAction_GETHOMEPAGE(@Query("areaid") String areaid,@Query("pageNO") int pageNO,@Query("pageSize") int pageSize,
                                              @Query("user_id") String user_id);

    @GET(ApiUrl.FzAction_SEARCHPREMISE)
    Observable<MainBean> FzAction_SEARCHPREMISE(@Query("premiseName") String premiseName ,@Query("pageNO") int pageNO,@Query("pageSize") int pageSize,
                                              @Query("areaid") String areaid);

    @GET(ApiUrl.FzAction_GETCOUNTYPRIMISE)
    Observable<MainBean> FzAction_GETCOUNTYPRIMISE(@Query("countyID") String countyID );

    @GET(ApiUrl.FzAction_getHouseType)
    Observable<HouseTypes> FzAction_getHouseType(@Query("preid") String preid );

    @GET(ApiUrl.FzAction_getHouseDesign)
    Observable<HotspotBean> FzAction_getHouseDesign(@Query("house_id") String house_id, @Query("user_id") String user_id );

    @GET(ApiUrl.FzAction_getScene)
    Observable<RoomBean> FzAction_getScene(@Query("hot_id") String hot_id, @Query("is_collected") String is_collected,@Query("user_id") String user_id );

    @GET(ApiUrl.FzAction_getMaterialList)
    Observable<BaseBean> FzAction_getMaterialList(@Query("design_id") String design_id,@Query("user_id") String user_id );

    @GET(ApiUrl.FzAction_getOtherScene)
    Observable<RestRoom> FzAction_getOtherScene(@Query("design_id") String design_id, @Query("user_id") String user_id , @Query("hot_id") String hot_id);

    @POST(ApiUrl.FzAction_collectDesign)
    @FormUrlEncoded
    Observable<BaseBean> FzAction_collectDesign(@Field("scene_id") String scene_id, @Field("plainText") String plainText,
                                                @Field("user_id") String user_id, @Field("scene_img") String scene_img);

    @POST(ApiUrl.MERGEPICTURE)
    @FormUrlEncoded
    Observable<Sceneorderback> getMergePicture(@Field("plainText") String plaintext);

    @GET(ApiUrl.GETMATERIALLIST)
    Observable<MaterialBean> GETMATERIALLIST(@Query("design_id") String design_id, @Query("user_id") String user_id);

    @GET(ApiUrl.GETCOLLECTLIST)
    Observable<CollectBean> GETCOLLECTLIST(@Query("user_id") String user_id);

    @GET(ApiUrl.GETSHOPCARTLIST)
    Observable<ShopBean> GETSHOPCARTLIST(@Query("user_id") String user_id);

}
