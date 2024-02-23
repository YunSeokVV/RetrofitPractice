package com.example.retrofitpracticewithdustapi.dataSource

import com.example.retrofitpracticewithdustapi.model.DustModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface DustApiService {
    @GET("getCtprvnMesureLIst?")
    suspend fun getDustInformation(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("returnType", encoded = true) returnType: String,
        @Query("numOfRows", encoded = true) numOfRows: String,
        @Query("pageNo", encoded = true) pageNo: String,
        @Query("itemCode", encoded = true) itemCode: String,
        @Query("dataGubun", encoded = true) dataGubun: String,
        @Query("searchCondition", encoded = true) searchCondition: String,
    ): Response<DustModel>
}