package com.example.retrofitpracticewithdustapi.useCase

import com.example.retrofitpracticewithdustapi.Citys
import com.example.retrofitpracticewithdustapi.repository.DustRepository

// 솔직히 지금 이 예제에서는 useCase를 쓰는 별 큰 이유가 없다. useCase 를 썼을때 장점을 전혀 활용할수가 없는 상황이다. 그냥 외부에서 데이터 하나 읽어오는게 전부인 예제니까.
//useCase 의 장점을 제대로 활용하려면 같은 로직이 반복되서 중복되는 코드가 있어야 한다.
//이 예제는 openApi를 사용해서 대한민국의 각 시별로 미세먼지 농도값을 구하는 기능을 하는데, 다른 openApi 를 통해서 조회를 하는 메소드가 많고 조회 하는 로직이 비슷할때 useCase를 쓰는 의미가 있다.
//일단 쓰는 방법 자체를 알아야 하는 거니까 useCase적용해서 하긴 해야지.
class DustUseCase(private val dustRepository: DustRepository) {
    fun execute(callback : (Citys) -> Unit){
        dustRepository.makeApiCall(callback)
    }
}