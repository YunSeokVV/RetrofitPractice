package com.example.retrofitpracticewithdustapi.model

import com.example.retrofitpracticewithdustapi.Body
import com.google.gson.annotations.SerializedName

data class CityTemp(
    @SerializedName("body") val body: Body,
    @SerializedName("header") val header: Object
)