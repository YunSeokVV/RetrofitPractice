package com.example.retrofitpracticewithdustapi.dataSource

import com.example.retrofitpracticewithdustapi.BuildConfig
import com.example.retrofitpracticewithdustapi.model.DustModel

// 이 클래스는 DustDataSource 인터페이스를 구현한 구현체다. layer 를 나누어서 사용할때 핵심은 의존성 주입, 역전을 활용해서 객체간의 결합도를 낮추는 것이다.
class DustRemoteDataSource(private val dustApiService: DustApiService) : DustDataSource{

    override suspend fun getDust(): DustModel {
        // 아래에 매개변수를 어떤 방법으로 넣어야 할까?
        return dustApiService.getDustInformation(BuildConfig.OPENAPI_CLIENT_ID,  "json", "100", "1",  "PM10", "HOUR", "MONTH")
    }

}