package com.example.retrofitpracticewithdustapi.repository

import com.example.retrofitpracticewithdustapi.BuildConfig
import com.example.retrofitpracticewithdustapi.dataSource.DustApiService
import javax.inject.Inject


class DustRepository @Inject constructor(private val dustApiService: DustApiService) {
    suspend fun getDustInformation() = dustApiService.getDustInformation(BuildConfig.OPENAPI_CLIENT_ID,  "json", "100", "1",  "PM10", "HOUR", "MONTH")
}