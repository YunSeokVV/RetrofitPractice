package com.example.retrofitpracticewithdustapi.dataSource

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.IOException

class DataSourceUtil {

    fun getEncodedClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // 인터셉터 : 통신을 하기전(응답 or 요청) 값을 가로채서 가공시킨다. HttpRequest나 HttpResponse를 가공한다.
            // addInterceptor : 연결이 설정되기 전(있는 경우)부터 응답 소스가 선택된 후(원본 서버, 캐시 또는 둘 다)까지 각 호출의 전체 범위를 관찰하는 수정 가능한 인터셉터 목록을 반환합니다.
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private class AuthInterceptor : Interceptor {
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