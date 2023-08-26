package com.example.retrofitpracticewithdustapi

import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("items") val items:List<Citys>
)
