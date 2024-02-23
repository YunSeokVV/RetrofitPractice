package com.example.retrofitpracticewithdustapi.useCase

import com.example.retrofitpracticewithdustapi.repository.DustRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDust @Inject constructor(private val dustRepository: DustRepository) {
    suspend fun getDustInformation() = flow{
        emit(dustRepository.getDustInformation())
    }
}