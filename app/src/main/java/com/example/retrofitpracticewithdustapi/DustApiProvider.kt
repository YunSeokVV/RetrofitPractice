package com.example.retrofitpracticewithdustapi

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.io.IOException
// 이 파일이 가장 핵심이다.
// object 예약어를 사용하면 싱글톤으로 객체를 생성할 수 있게 된다. 혼자서 Retrofit 을 공부하면서 중복되던 코드를 이렇게 하면 줄일 수 있다.
object DustApiProvider {
    fun provideDustApi(): DustApi{
       return Retrofit.Builder()
           .baseUrl("http://apis.data.go.kr/B552584/ArpltnStatsSvc/")
           //client() : The HTTP client used for requests.
           .client(getEncodedClient())
           .addConverterFactory(GsonConverterFactory.create())
           //.addConverterFactory(ScalarsConverterFactory.create())

           .build()
           .create(DustApi::class.java)
    }

    private fun getEncodedClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // 인터셉터 : 통신을 하기전(응답 or 요청) 값을 가로채서 가공시킨다. HttpRequest나 HttpResponse를 가공한다.
            // addInterceptor : 연결이 설정되기 전(있는 경우)부터 응답 소스가 선택된 후(원본 서버, 캐시 또는 둘 다)까지 각 호출의 전체 범위를 관찰하는 수정 가능한 인터셉터 목록을 반환합니다.
            .addInterceptor(AuthInterceptor())
            .build()
    }

    internal class AuthInterceptor : Interceptor{
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            // 현재 요청을 실행하여 원래 서버로부터 응답을 가져옵니다.
            val response = chain.proceed(chain.request())
            // 가져온 응답에 "Content-Type" 헤더를 추가하고, 이 헤더의 값은 "application/json; charset=utf-8"입니다.
            //이렇게 함으로써 서버에게 이 요청이 JSON 형식의 데이터를 주고받을 것임을 알립니다. 그리고 수정한 헤더를 포함한 새로운 응답을 반환합니다.
            return response.newBuilder().addHeader("Content-Type", "application/json; charset=utf-8").build()
        }

    }

}