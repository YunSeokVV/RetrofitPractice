package com.example.retrofitpracticewithdustapi.viewModel

import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpracticewithdustapi.model.DustModel
import com.example.retrofitpracticewithdustapi.useCase.GetDust

import com.google.gson.JsonSyntaxException
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val dustUseCase: GetDust) : ViewModel() {
    private val _cityTemp = MutableLiveData<DustModel>()
    val cityTemp : LiveData<DustModel> get() {return _cityTemp}


    private val _downComplete = MutableLiveData<Int>()
    val downComplete : LiveData<Int> get(){return _downComplete}


    private val _errorStatusLiveData = MutableLiveData<Throwable>()
    val errorStatusLiveData : LiveData<Throwable> get() { return _errorStatusLiveData}

    init {
        loadTemp()
    }

    // 예제에서는 ViewModelScope 를 쓰고 있다. viewModelScope 가 무엇인지 모르면 이 이상 코딩하기는 어렵다.
    private fun loadTemp() {
        //프로그레스바 보이게
        _downComplete.value = View.VISIBLE

        //ViewModelScope : 뷰모델과 엮여있는 코루틴영역이다. ViewModel이 clear되면 이 영역(scope)도 사라진다. 예를 들면 viewModel.onCleared가 호출될때
        //launch : 스레드의 실행을 중지하지 않고 새로운 코루틴을 실행시키고 코루틴에게 Job객체를 새로 반환시켜준다. 결과 job이 취소되면 코루틴도 취소된다.
        // 그냥 쉽게 의역하자면 뷰모델이 종료되면 코루틴도 따라서 종료된다.
        viewModelScope.launch {
            try {

                // 서버와 통신 성공한 경우
                if (dustUseCase.getDustInformation().isSuccessful) {
                    Logger.v(dustUseCase.getDustInformation().message())
                    Logger.v(dustUseCase.getDustInformation().body().toString())
                    _cityTemp.value = dustUseCase.getDustInformation().body()

                    //프로그레스바 안보이게
                    _downComplete.value = View.GONE
                }
            } catch (throwable: Throwable) {
                _errorStatusLiveData.value = throwable
            }
        }
    }
}