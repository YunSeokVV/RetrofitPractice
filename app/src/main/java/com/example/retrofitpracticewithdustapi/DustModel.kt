package com.example.retrofitpracticewithdustapi

import com.google.gson.annotations.SerializedName

data class DustModel(
    @SerializedName("response") val response: Response

)
