package com.example.retrofitpracticewithdustapi.useCase

import com.example.retrofitpracticewithdustapi.model.CityTemp
import com.example.retrofitpracticewithdustapi.model.DustModel
import com.example.retrofitpracticewithdustapi.repository.DustRepository
import retrofit2.Call
import retrofit2.Response

interface DustUseCase {
    suspend fun getDustInformation() : Response<DustModel>
}

class DustUseCaseImpl(private val dustRepository: DustRepository) : DustUseCase {
    override suspend fun getDustInformation(): Response<DustModel> {
        return dustRepository.getDustInformation()
    }
}