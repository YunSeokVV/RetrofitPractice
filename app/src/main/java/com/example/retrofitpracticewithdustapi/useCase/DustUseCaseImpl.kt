package com.example.retrofitpracticewithdustapi.useCase

import com.example.retrofitpracticewithdustapi.model.CityTemp
import com.example.retrofitpracticewithdustapi.model.DustModel
import com.example.retrofitpracticewithdustapi.repository.DustRepository

interface DustUseCase {
    suspend fun getDustInformation() : CityTemp
}

class DustUseCaseImpl(private val dustRepository: DustRepository) : DustUseCase {
    override suspend fun getDustInformation(): CityTemp {
        return dustRepository.getDustInformation()
    }
}