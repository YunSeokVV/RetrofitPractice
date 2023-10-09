package com.example.retrofitpracticewithdustapi.repository

import android.util.Log
import com.example.retrofitpracticewithdustapi.BuildConfig
import com.example.retrofitpracticewithdustapi.Citys
import com.example.retrofitpracticewithdustapi.DustApi
import com.example.retrofitpracticewithdustapi.DustApiProvider
import com.example.retrofitpracticewithdustapi.DustModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DustRepository() {
    val api = DustApiProvider.provideDustApi()

    // repository에서 호출해서 사용하기 위한 메소드다.
    fun makeApiCall(callback : (Citys) -> Unit) {
        // 아래 코드에서 이미 통신을 한다는 얘기다. 그 결과값이 dust에 담기는거고.
        val dust = api.getDustInformation(BuildConfig.OPENAPI_CLIENT_ID,  "json", "100", "1",  "PM10", "HOUR", "MONTH")
        //println(dustCall.request().url)
        //enqueue : 비동저기적으로 요청과 알림 콜백의 응답을 보내거나 서버와 통신했을때 에러가 일어난경우, 요청을 만들거나 응답을 처리한다.
        dust.enqueue(object: Callback<DustModel> {
            override fun onResponse(call: Call<DustModel>, response: Response<DustModel>) {
                val cityResult : Citys? = response.body()?.response?.body?.items?.get(0)
                cityResult?.let { callback(it) }
            }

            override fun onFailure(call: Call<DustModel>, t: Throwable) {
            }
        })
    }

}