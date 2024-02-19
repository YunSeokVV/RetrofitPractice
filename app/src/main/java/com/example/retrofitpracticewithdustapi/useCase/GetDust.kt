package com.example.retrofitpracticewithdustapi.useCase

import com.example.retrofitpracticewithdustapi.repository.DustRepository
import javax.inject.Inject

class GetDust @Inject constructor(private val dustRepository: DustRepository) {
    suspend fun getDustInformation(
    ) = dustRepository.getDustInformation()
}