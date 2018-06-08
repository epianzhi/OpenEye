package com.qj.kaiyan.http;


import com.qj.kaiyan.base.FindResult;
import com.qj.kaiyan.entitys.HomeResult;
import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.mvp.model.bean.FindDetailResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    //获取首页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<HomeResult> getHomeData();
    //获取首页数据
    @GET("v2/feed")
    Observable<HomeResult> getHomeMoreData(@Query("date") String date,@Query("num") String num);
    //获取发现页面数据
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<List<FindResult>> getFindData();
    //获取发现详情页面数据
    @GET("v3/videos")
    Observable<FindDetailResult> getFindDetailData(@Query("categoryName") String categoryName, @Query("strategy") String strategy, @Query("udid") String udid, @Query("vc") int vc);
    @GET("v3/videos")
    Observable<FindDetailResult> getFindDetailMoreData(@Query("start") int start,@Query("num") int num,@Query("categoryName") String categoryName, @Query("strategy") String strategy);

    //获取热门排行
    @GET("v3/ranklist")
    Observable<FindDetailResult> getRankData(@Query("num")int num,@Query("strategy")String strategy,@Query("udid")String udid,@Query("vc")int vc);
    //搜索
    @GET("v1/search")
    Observable<FindDetailResult> getSearchData(@Query("num")int num,@Query("query")String query,@Query("start") int start);
}
