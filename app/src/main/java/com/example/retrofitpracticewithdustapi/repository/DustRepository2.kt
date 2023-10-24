package com.example.retrofitpracticewithdustapi.repository

import com.example.retrofitpracticewithdustapi.model.CityTemp

// useCase layer가 추가되기 전에는 Repsotiroy가 class였다. (인터페이스가 아니였다.) 하지만 useCase 가 추가되면서 interface가 됐는데 이는 다형성(리스코프 치환원칙)을 활용해서
//더욱 다양한 RemoteApi나 LocalApi를 사용하기 위해서이다. 이전 커밋 내역을 확인하면 DustRepository 가 인터페이스가 아닌 클래스임을 확인할 수 있다.
interface DustRepository2 {
    suspend fun getDustInformation() : CityTemp
}