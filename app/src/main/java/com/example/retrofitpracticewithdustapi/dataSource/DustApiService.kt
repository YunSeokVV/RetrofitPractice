package com.example.retrofitpracticewithdustapi.dataSource

import com.example.retrofitpracticewithdustapi.model.DustModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// 공식문서및 많은 블로그내용을 봤는데 인터페이스는 보통 가장 마지막에 ~Service로 이름을 붙인다.
//그리고 RetrofitApiService 인터페이스를 사용하는 Retrofit 객체는 코드가 반복되는 경우가 엄청 많다. 그래서 이 파일안에 내용을 전부 넣은거다.
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

    companion object{
//        fun provideDustApi() : DustApiService {
//            return Retrofit.Builder()
//                .baseUrl("http://apis.data.go.kr/B552584/ArpltnStatsSvc/")
//                //client() : The HTTP client used for requests.
//                .client(DataSourceUtil().getEncodedClient())
//                .addConverterFactory(GsonConverterFactory.create())
//                //.addConverterFactory(ScalarsConverterFactory.create())
//                .build()
//                .create(DustApiService::class.java)
//        }

        inline fun <reified T: Any> providerApi(): T {
            return Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/B552584/ArpltnStatsSvc/")
                //client() : The HTTP client used for requests.
                .client(DataSourceUtil().getEncodedClient())
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(T::class.java)
        }
    }
}