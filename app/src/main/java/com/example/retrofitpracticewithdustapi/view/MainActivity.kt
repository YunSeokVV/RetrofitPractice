// 공공데이터를 보고 만든 전국별 평균 미세먼지량을 구하는 예제 Retrofit 을 사용해서 원하는 데이터를 파싱한다. 서버에서 받아온 데이터는 json 형식이고 xml 로 받을수도 있다.
// repository 패턴까지 적용해서 서버에서 응답을 받아오는 기능을 제공해준다.

//DustApiProvider 파일을 제대로 익혀야 한다.
package com.example.retrofitpracticewithdustapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofitpracticewithdustapi.Citys
import com.example.retrofitpracticewithdustapi.R
import com.example.retrofitpracticewithdustapi.dataSource.DustApiService
import com.example.retrofitpracticewithdustapi.dataSource.DustDataSource
import com.example.retrofitpracticewithdustapi.dataSource.DustRemoteDataSource
import com.example.retrofitpracticewithdustapi.repository.DustRepository
import com.example.retrofitpracticewithdustapi.repository.DustRepositoryImpl
import com.example.retrofitpracticewithdustapi.useCase.DustUseCase
import com.example.retrofitpracticewithdustapi.useCase.DustUseCaseImpl
import com.example.retrofitpracticewithdustapi.viewModel.MainActivityViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MainActivity : AppCompatActivity() {

    // by viewModels 를 사용하면 ViewModelProvider를 사용하지 않고 viewModel을 지연 생성하는게 가능하다.
    private val viewModel: MainActivityViewModel by viewModels {
        viewModelFactory {
            initializer {
                MainActivityViewModel(
                    DustUseCaseImpl(
                        DustRepositoryImpl(
                            DustRemoteDataSource(
                                DustApiService.providerApi()
                            )
                        )
                    )
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())

        val textView: TextView = findViewById(R.id.textView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        viewModel.getCityTemp().observe(this, Observer { data ->
            Logger.v(data.toString())
            textView.text = data.toString()
            //progressBar.visibility = View.VISIBLE
        })

        viewModel.getIsDownComplete().observe(this, Observer { data ->
            Logger.v(data.toInt().toString())
            progressBar.visibility = data.toInt()
        })

        viewModel.getErrorStatusLiveData().observe(this, Observer {
            when(it){
                MainActivityViewModel.ApiStatus.LOADING -> {
                    Logger.v("Loading Now ...")
                }
                MainActivityViewModel.ApiStatus.ERROR -> {
                    Logger.v("Network Error")
                }
                MainActivityViewModel.ApiStatus.DONE -> {
                    Logger.v("Loading complete")
                }
            }
        })

    }

}