package com.example.retrofitpracticewithdustapi.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpracticewithdustapi.dataSource.DustApiService
import com.example.retrofitpracticewithdustapi.dataSource.DustRemoteDataSource
import com.example.retrofitpracticewithdustapi.repository.DustRepository
import java.lang.IllegalArgumentException

class ViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainActivityViewModel::class.java)->{
                val repository = DustRepository(DustRemoteDataSource(DustApiService.providerApi<DustApiService>()))
                MainActivityViewModel(repository) as T
            }
            else ->{
                throw IllegalArgumentException("Failed to create viewModel : ${modelClass.name} ")
            }
        }
    }
}
