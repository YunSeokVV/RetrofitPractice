package com.example.retrofitpracticewithdustapi.dataSource

import com.example.retrofitpracticewithdustapi.model.DustModel


interface DustDataSource {
    // 추상클래스에서는 코루틴 스코프를 따로 정의하지 않는다. 구현체에서 코루틴 스코프를 정의한다.
    suspend fun getDust() : DustModel
}