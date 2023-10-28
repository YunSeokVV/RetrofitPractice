package com.example.retrofitpracticewithdustapi.viewModel

import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpracticewithdustapi.dataSource.DustApiService
import com.example.retrofitpracticewithdustapi.dataSource.DustRemoteDataSource
import com.example.retrofitpracticewithdustapi.model.CityTemp
import com.example.retrofitpracticewithdustapi.model.DustModel
import com.example.retrofitpracticewithdustapi.repository.DustRepositoryImpl
import com.example.retrofitpracticewithdustapi.useCase.DustUseCase
import com.example.retrofitpracticewithdustapi.useCase.DustUseCaseImpl
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException

// 생성자안에 매개변수가 들어가는경우 ViewModel을 뷰단에서 ViewModelProvider 를 사용해서 생성할 수 없다. 매개변수를 넣고 싶다면 ViewModelFactory를 사용해야 한다.
class MainActivityViewModel(private val dustUseCase: DustUseCase) : ExceptionViewModel() {
    private var cityTemp = MutableLiveData<DustModel>()
    private var isDownComplete = MutableLiveData<Int>()

    init {
        loadTemp()
    }

    // 예제에서는 ViewModelScope 를 쓰고 있다. viewModelScope 가 무엇인지 모르면 이 이상 코딩하기는 어렵다.
    private fun loadTemp() {
        //프로그레스바 보이게
        isDownComplete.value = View.VISIBLE

        //ViewModelScope : 뷰모델과 엮여있는 코루틴영역이다. ViewModel이 clear되면 이 영역(scope)도 사라진다. 예를 들면 viewModel.onCleared가 호출될때
        //launch : 스레드의 실행을 중지하지 않고 새로운 코루틴을 실행시키고 코루틴에게 Job객체를 새로 반환시켜준다. 결과 job이 취소되면 코루틴도 취소된다.
        // 그냥 쉽게 의역하자면 뷰모델이 종료되면 코루틴도 따라서 종료된다.
        viewModelScope.launch(exceptionHandler) {

            // 서버와 통신 성공한 경우
            if (dustUseCase.getDustInformation().isSuccessful) {
                Logger.v(dustUseCase.getDustInformation().message())
                Logger.v(dustUseCase.getDustInformation().body().toString())
                cityTemp.value = dustUseCase.getDustInformation().body()

                //프로그레스바 안보이게
                isDownComplete.value = View.GONE
            }
            //cityTemp.setValue(dustUseCase.getDustInformation())
        }
    }

    fun getCityTemp(): LiveData<DustModel> {
        return cityTemp
    }

    fun getIsDownComplete() : LiveData<Int> {
        return isDownComplete
    }

}