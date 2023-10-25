package com.example.retrofitpracticewithdustapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpracticewithdustapi.dataSource.DustApiService
import com.example.retrofitpracticewithdustapi.dataSource.DustRemoteDataSource
import com.example.retrofitpracticewithdustapi.useCase.DustUseCase
import java.lang.IllegalArgumentException

// 더이상 사용하지 않는 코드다. ViewModel의 생성자에 파라미터가 있다면 보통 아래와 같은 방법으로 따로 Factory 클래스로 구현해줘야 한다. 하지만 그렇게 하지 않고도 구현하는 방법이 존재한다.
//그 방법은 view 단에서(MainActivity) 확인해볼것.
//class ViewModelFactory(private val dustUseCase: DustUseCase) : ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when{
//            modelClass.isAssignableFrom(MainActivityViewModel::class.java)->{
//                //아래와 같은 방법으로 한번은 만들어야 한다.
//                val repository = DustRepository(DustRemoteDataSource(DustApiService.providerApi<DustApiService>()))
//                //val repository2 = DustRepository2()
//                val useCase = DustUseCaseImpl(DustRepository2)
//                MainActivityViewModel(repository) as T
//            }
//            else ->{
//                throw IllegalArgumentException("Failed to create viewModel : ${modelClass.name} ")
//            }
//        }
//    }
//}
