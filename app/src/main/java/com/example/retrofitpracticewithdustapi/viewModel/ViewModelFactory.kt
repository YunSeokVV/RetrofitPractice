package com.example.retrofitpracticewithdustapi.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpracticewithdustapi.dataSource.DustApiService
import com.example.retrofitpracticewithdustapi.dataSource.DustRemoteDataSource
import com.example.retrofitpracticewithdustapi.repository.DustRepository
import com.example.retrofitpracticewithdustapi.repository.DustRepository2
import com.example.retrofitpracticewithdustapi.useCase.DustUseCase
import java.lang.IllegalArgumentException

class ViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainActivityViewModel::class.java)->{
                //아래와 같은 방법으로 한번은 만들어야 한다.
                val repository = DustRepository(DustRemoteDataSource(DustApiService.providerApi<DustApiService>()))
                //val repository2 = DustRepository2()
                // todo : 코드의 흐름이 useCase -> Repository -> RemoteDatasource -> RemoteData 순으로 흘러야 할텐데.. 
                val useCase = DustUseCase(DustRepository2)
                MainActivityViewModel(repository) as T
            }
            else ->{
                throw IllegalArgumentException("Failed to create viewModel : ${modelClass.name} ")
            }
        }
    }
}
