// 공공데이터를 보고 만든 전국별 평균 미세먼지량을 구하는 예제 Retrofit 을 사용해서 원하는 데이터를 파싱한다. 서버에서 받아온 데이터는 json 형식이고 xml 로 받을수도 있다.
// repository 패턴까지 적용해서 서버에서 응답을 받아오는 기능을 제공해준다.

// Flow 를 적용해봤다.
// 아래 예제를 보고 도움을 받았다. 예제 자체가 정말 좋다. 내 로컬 디렉터리에 "Kotlin-Coroutines-and-Flow-UseCases-on-Android-master" 이름으로 넣어놨다. 아래는 예제 출처
//https://github.com/LukasLechnerDev/Kotlin-Coroutines-and-Flow-UseCases-on-Android

// 테스트 요청값
// http://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureLIst?&serviceKey=HFjaGhGO7Imj5Tnp9ZjKKyPHiTk%2FNttWK1iW2oKrYopQuQon846dM9s9a8HqO9r25Es05j2Yc4FTzgOC1%2FVEBg%3D%3D&returnType=json&numOfRows=100&pageNo=1&itemCode=PM10&dataGubun=HOUR&searchCondition=MONTH

package com.example.retrofitpracticewithdustapi.view

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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