// 공공데이터를 보고 만든 전국별 평균 미세먼지량을 구하는 예제 Retrofit 을 사용해서 원하는 데이터를 파싱한다. 서버에서 받아온 데이터는 json 형식이고 xml 로 받을수도 있다.
//참고가 됐던 사이트 https://velog.io/@24hyunji/Retrofit-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
//참고했던 공공데이터포털 사이트 링크 : https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15073855
//멘토님이 참고하라고 알려주신 사이트 링크 : https://blog.naver.com/scy7351/221678263971

//DustApiProvider 파일을 제대로 익혀야 한다.
package com.example.retrofitpracticewithdustapi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder
import java.util.Objects

class MainActivity : AppCompatActivity() {
    lateinit var api : DustApi
    //val repos : Call<DustModel> = apiService.plantImage(plantID)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api = DustApiProvider.provideDustApi()
        //makeApiCall()
        makeApiCall()
    }

    private fun makeApiCall() {
        val dust = api.getDustInformation(BuildConfig.OPENAPI_CLIENT_ID,  "json", "100", "1",  "PM10", "HOUR", "MONTH")
        //println(dustCall.request().url)
        dust.enqueue(object: Callback<DustModel> {
            override fun onResponse(call: Call<DustModel>, response: Response<DustModel>) {
                println("onResponse")

                println("result")
                println(response.body()?.response?.body?.items?.get(0))
            }

            override fun onFailure(call: Call<DustModel>, t: Throwable) {
                println("onFailure")
                println(t.printStackTrace())
            }
        })
    }

}