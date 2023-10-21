package com.example.retrofitpracticewithdustapi.model

import com.google.gson.annotations.SerializedName

data class DustModel(
    @SerializedName("response") val cityTemp: CityTemp

)
