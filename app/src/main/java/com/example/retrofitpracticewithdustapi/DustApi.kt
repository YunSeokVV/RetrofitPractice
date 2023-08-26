package com.example.retrofitpracticewithdustapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Objects

interface DustApi {
    @GET("getCtprvnMesureLIst?")
    fun getDustInformation(
        @Query("serviceKey", encoded = true) serviceKey : String,
        @Query("returnType", encoded = true) returnType : String,
        @Query("numOfRows", encoded = true) numOfRows: String,
        @Query("pageNo", encoded = true) pageNo : String,
        @Query("itemCode", encoded = true) itemCode : String,
        @Query("dataGubun", encoded = true) dataGubun : String,
        @Query("searchCondition", encoded = true) searchCondition : String,
    ): Call<DustModel>
}