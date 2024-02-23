package com.example.retrofitpracticewithdustapi.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpracticewithdustapi.model.DustModel
import com.example.retrofitpracticewithdustapi.useCase.GetDust
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val dustUseCase: GetDust) : ViewModel() {
    private val _cityTemp = MutableLiveData<DustModel>()

    val cityTemp: LiveData<DustModel>
        get() {
            return _cityTemp
        }

    private val _downComplete = MutableLiveData<Int>()
    val downComplete: LiveData<Int>
        get() {
            return _downComplete
        }


    private val _errorStatusLiveData = MutableLiveData<Throwable>()
    val errorStatusLiveData: LiveData<Throwable>
        get() {
            return _errorStatusLiveData
        }

    init {
        loadTemp()
    }


    private fun loadTemp() {
        _downComplete.value = View.VISIBLE
        viewModelScope.launch {

            try {
                dustUseCase.getDustInformation().collect { result ->
                    if (result.isSuccessful) {
                        Logger.v(result.body().toString())
                        _cityTemp.value = result.body()
                        _downComplete.value = View.GONE
                    }
                }
            } catch (throwable : Throwable){
                _errorStatusLiveData.value = throwable
            }
        }
    }

}