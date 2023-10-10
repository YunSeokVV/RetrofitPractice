package com.example.retrofitpracticewithdustapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitpracticewithdustapi.Citys
import com.example.retrofitpracticewithdustapi.repository.DustRepository
import com.example.retrofitpracticewithdustapi.useCase.DustUseCase

// 생성자안에 매개변수가 들어가는경우 ViewModel을 뷰단에서 ViewModelProvider 를 사용해서 생성할 수 없다. 매개변수를 넣고 싶다면 ViewModelFactory를 사용해야 한다.
class MainActivityViewModel() : ViewModel(){
    private val dustRepository = DustRepository()
    private val dustUseCase = DustUseCase(dustRepository)

    // 이거 꼭 MutableLiveData로 해야함? LiveData 하면 안됨?
    private var cityTemp = MutableLiveData<Citys>()

    fun fetchData(){
//        dustRepository.makeApiCall{data ->
//            cityTemp.postValue(data)
//        }

        dustUseCase.execute { data ->
            cityTemp.postValue(data)
        }

    }

    fun getCityTemp() : MutableLiveData<Citys>{
        return cityTemp
    }

}