package com.example.retrofitpracticewithdustapi.useCase

import com.example.retrofitpracticewithdustapi.Citys
import com.example.retrofitpracticewithdustapi.model.CityTemp
import com.example.retrofitpracticewithdustapi.repository.DustRepository2
import retrofit2.Callback

//todo : useCase 는 반드시 repository 인터페이스를 구현해야 하는 것으로 알고 있다. 하지만 예제를 찾아봤을때 그렇지 않은 경우도 있다. 왜지?
class DustUseCase(private val dustRepository2: DustRepository2) : DustRepository2{


    override suspend fun getDustInformation(): CityTemp {
        return dustRepository2.getDustInformation()
    }

//    suspend fun getDustInformation() : CityTemp{
//        return dustRepository2.getDustInformation()
//    }
}