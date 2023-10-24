package com.example.retrofitpracticewithdustapi.viewModel

import android.os.Looper
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpracticewithdustapi.DustUseCaseImpl
import com.example.retrofitpracticewithdustapi.model.CityTemp
import com.example.retrofitpracticewithdustapi.repository.DustRepository
import com.example.retrofitpracticewithdustapi.useCase.DustUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

// 생성자안에 매개변수가 들어가는경우 ViewModel을 뷰단에서 ViewModelProvider 를 사용해서 생성할 수 없다. 매개변수를 넣고 싶다면 ViewModelFactory를 사용해야 한다.
//class MainActivityViewModel(private val dustRepository: DustRepository) : ViewModel(){
class MainActivityViewModel(dustUseCase: DustUseCase) : ViewModel(){
    //지연님 피드백
    private val repository = DustUseCaseImpl()
    private var cityTemp = MutableLiveData<CityTemp>()

    init{
        loadTemp()
    }

    // 예제에서는 ViewModelScope 를 쓰고 있다. viewModelScope 가 무엇인지 모르면 이 이상 코딩하기는 어렵다.
    private fun loadTemp(){
        //ViewModelScope : 뷰모델과 엮여있는 코루틴영역이다. ViewModel이 clear되면 이 영역(scope)도 사라진다. 예를 들면 viewModel.onCleared가 호출될때
        //launch : 스레드의 실행을 중지하지 않고 새로운 코루틴을 실행시키고 코루틴에게 Job객체를 새로 반환시켜준다. 결과 job이 취소되면 코루틴도 취소된다.
        // 그냥 쉽게 의역하자면 뷰모델이 종료되면 코루틴도 따라서 종료된다.
        viewModelScope.launch {
            //cityTemp.value = dustRepository.getDustInformation()

            // 백그라운드 스레드에서 값을 넣으려고 할때 postValue를 써야 한다. 메인스레드에서 값을 넣으려고 할때는 setValue를 쓴다.
            //postValue 는 thread-safe 하지 않다. thread-safe하기 위해서는 setValue로 바꾸고 메인스레드에서 동작하게끔 변형시켜줘야 한다.
            //todo : 그리고 이를 위해서 사용하는게 handler 이다. 하지만 공부한 자료에 따르면 postValue를 사용하면 아래와 같이 내부 코드가 실행된다고 한다. 그러면 내가 따로 handler로 처리해야 하는게 있는건가?
            // new Handler(Looper.mainLooper()).post(() -> setValue())
            //cityTemp.postValue(dustRepository.getDustInformation())

            // 로그로 확인해보니 이 시점의 스레드는 메인스레드였다. 메인스레드에서는 setValue를 쓰는게 맞다.
            Logger.v(Thread.currentThread().id.toString())
            Logger.v(Looper.getMainLooper().thread.id.toString())
            cityTemp.setValue(repository.getDustInformation())
            //cityTemp.setValue(dustUseCase.getDustInformation())
        }
    }



    fun getCityTemp() : LiveData<CityTemp>{
        return cityTemp
    }

}