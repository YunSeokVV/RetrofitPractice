package com.example.retrofitpracticewithdustapi.repository

import com.example.retrofitpracticewithdustapi.dataSource.DustDataSource
import com.example.retrofitpracticewithdustapi.model.CityTemp
import com.example.retrofitpracticewithdustapi.model.DustModel


interface DustRepository {
    suspend fun getDustInformation() : CityTemp
}

class DustRepositoryImpl(private val dustDataSource: DustDataSource) : DustRepository{
    override suspend fun getDustInformation(): CityTemp {
        return dustDataSource.getDust().cityTemp
    }


}