package com.example.retrofitpracticewithdustapi.module

import com.example.retrofitpracticewithdustapi.Constants
import com.example.retrofitpracticewithdustapi.dataSource.DustApiService
import com.example.retrofitpracticewithdustapi.repository.DustRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton
import kotlin.math.log

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    fun setBaseURL() = Constants.BASE_URL
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl(setBaseURL())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): DustApiService {
        return retrofit.create(DustApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDustRepository(dustApiService: DustApiService) = DustRepository(dustApiService)

}