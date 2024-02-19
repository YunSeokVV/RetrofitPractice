// 공공데이터를 보고 만든 전국별 평균 미세먼지량을 구하는 예제 Retrofit 을 사용해서 원하는 데이터를 파싱한다. 서버에서 받아온 데이터는 json 형식이고 xml 로 받을수도 있다.
// repository 패턴까지 적용해서 서버에서 응답을 받아오는 기능을 제공해준다.

// Hilt라이브러리를 적용했다.
// 아래 예제를 보고 도움을 받았다. 정말 좋은 과정이였던게, 이전 커밋 내역을 보면 hilt를 안써서 의존성 주입을 직접 전부 해주고, 추상화와 그 구현체를 만드는 것 까지 수작업 한 것을 볼 수 있다.
//https://android-uni.tistory.com/34

package com.example.retrofitpracticewithdustapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.retrofitpracticewithdustapi.R
import com.example.retrofitpracticewithdustapi.viewModel.MainActivityViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())

        val textView: TextView = findViewById(R.id.textView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        viewModel.cityTemp.observe(this, Observer { data ->
            Logger.v(data.toString())
            textView.text = data.toString()
        })

        viewModel.downComplete.observe(this, Observer { data ->
            Logger.v(data.toInt().toString())
            progressBar.visibility = data.toInt()
        })

        viewModel.errorStatusLiveData.observe(this, Observer {
            Logger.v(it.message.toString())
            when(it){
                is UnknownHostException -> Toast.makeText(applicationContext, "UnknownHostException", Toast.LENGTH_SHORT).show()
                is TimeoutException -> Toast.makeText(applicationContext, "TimeoutException", Toast.LENGTH_SHORT).show()
                is JSONException -> Toast.makeText(applicationContext, "JSONException", Toast.LENGTH_SHORT).show()
                else ->  Toast.makeText(applicationContext, "Some Exception Occured", Toast.LENGTH_SHORT).show()
            }
        })

    }

}