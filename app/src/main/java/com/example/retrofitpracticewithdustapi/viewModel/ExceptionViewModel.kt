package com.example.retrofitpracticewithdustapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonSyntaxException
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import org.json.JSONException
import retrofit2.HttpException
import java.lang.IllegalStateException
import java.net.SocketException
import java.net.UnknownHostException

//출처 : https://bb-library.tistory.com/264
//https://velog.io/@skydoves/retrofit-api-handling-sandwich (Exception 처리를 도와주는 sandwich 라이브러리 사용방법)
// Coroutine의 context로 CoroutineExceptionHandling을 하는 방법이다.
open class ExceptionViewModel : ViewModel(){
    val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
        when(throwable){
            is JsonSyntaxException -> Logger.e("JsonSyntax")
            is IllegalStateException -> Logger.e("IllegalState")
            is JSONException -> Logger.e("JSON_ERROR")
            is SocketException -> Logger.e("BAD_INTERNET")
            is HttpException -> Logger.e("PARSE_ERROR")
            is UnknownHostException -> Logger.e("WRONG_CONNECTION")
            else -> Logger.e("FAIL")
        }
    }

}