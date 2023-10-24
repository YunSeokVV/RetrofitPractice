package com.example.retrofitpracticewithdustapi

import com.example.retrofitpracticewithdustapi.dataSource.DustRemoteDataSource
import com.example.retrofitpracticewithdustapi.model.CityTemp
import com.example.retrofitpracticewithdustapi.repository.DustRepository2

class DustRepositoryImpl : DustRepository2 {
    override suspend fun getDustInformation(): CityTemp {
        val dustRemoteDataSource = DustRemoteDataSource()
    }
}