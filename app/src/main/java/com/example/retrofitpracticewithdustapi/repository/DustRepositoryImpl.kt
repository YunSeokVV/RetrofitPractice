package com.example.retrofitpracticewithdustapi.repository

import com.example.retrofitpracticewithdustapi.dataSource.DustDataSource
import com.example.retrofitpracticewithdustapi.model.CityTemp
import com.example.retrofitpracticewithdustapi.model.DustModel
import retrofit2.Call
import retrofit2.Response


interface DustRepository {
    suspend fun getDustInformation() : Response<DustModel>
}

class DustRepositoryImpl(private val dustDataSource: DustDataSource) : DustRepository{
    override suspend fun getDustInformation(): Response<DustModel> {
        return dustDataSource.getDust()
    }


}