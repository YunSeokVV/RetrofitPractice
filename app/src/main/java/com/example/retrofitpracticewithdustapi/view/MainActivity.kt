// 공공데이터를 보고 만든 전국별 평균 미세먼지량을 구하는 예제 Retrofit 을 사용해서 원하는 데이터를 파싱한다. 서버에서 받아온 데이터는 json 형식이고 xml 로 받을수도 있다.
// repository 패턴까지 적용해서 서버에서 응답을 받아오는 기능을 제공해준다.

//DustApiProvider 파일을 제대로 익혀야 한다.
package com.example.retrofitpracticewithdustapi.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpracticewithdustapi.BuildConfig
import com.example.retrofitpracticewithdustapi.Citys
import com.example.retrofitpracticewithdustapi.DustApi
import com.example.retrofitpracticewithdustapi.DustApiProvider
import com.example.retrofitpracticewithdustapi.DustModel
import com.example.retrofitpracticewithdustapi.R
import com.example.retrofitpracticewithdustapi.viewModel.MainActivityViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    //방법2
    //private lateinit var viewModel : MainActivityViewModel

    //방법1
    private val viewModel:MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //api = DustApiProvider.provideDustApi()
        Logger.addLogAdapter(AndroidLogAdapter())

        //방법2
        //viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getCityTemp().observe(this, Observer {data ->
            updateUI(data)
        })
        viewModel.fetchData()
    }

    private fun updateUI(data: Citys){
        Logger.v(data.toString())
    }

}