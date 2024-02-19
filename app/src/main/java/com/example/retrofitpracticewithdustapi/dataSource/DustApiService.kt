package com.example.retrofitpracticewithdustapi.dataSource

import com.example.retrofitpracticewithdustapi.model.DustModel
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface DustApiService{
    @GET("getCtprvnMesureLIst?")
    suspend fun getDustInformation(
        @Query("serviceKey", encoded = true) serviceKey : String,
        @Query("returnType", encoded = true) returnType : String,
        @Query("numOfRows", encoded = true) numOfRows: String,
        @Query("pageNo", encoded = true) pageNo : String,
        @Query("itemCode", encoded = true) itemCode : String,
        @Query("dataGubun", encoded = true) dataGubun : String,
        @Query("searchCondition", encoded = true) searchCondition : String,
    // 코루틴의 suspend 를 사용하고 나면 Call 객체를 리턴받을 수 없다. Call을 쓰려면 Executor를 써야한다.
    //): Call<DustModel>
    ): Response<DustModel>
}