package com.example.retrofitpracticewithdustapi.repository

import com.example.retrofitpracticewithdustapi.dataSource.DustRemoteDataSource
import com.example.retrofitpracticewithdustapi.model.CityTemp

class DustRepository(private val dustRemoteDataSource: DustRemoteDataSource) {

    // 코루틴 susped 를 쓰게되면서 Call 의 enqueue 메소드를 쓸 수 없어서 onResponse 와 onFailure를 쓸 수 없게 됐다.
    // suspend 예약어가 붙음으로서 코루틴으로 사용될 것임을 나타낸다.
    suspend fun getDustInformation() : CityTemp {

        // 아래 메소드가 비동기적인 작업을 수행할 수 있고 이작업이 완료될때까지 getDustInformation 함수가 일시중지 된다.
        //그 후에는 작업이 완료되면 결과에 접근할 수 있게 되서 response를 반환한다.
        return dustRemoteDataSource.getDust().cityTemp
    }

}