package com.example.retrofitpracticewithdustapi

import com.google.gson.annotations.SerializedName

data class Citys(
    @SerializedName("daegu") val daegu : String,
    @SerializedName("chungnam") val chungnam : String,
    @SerializedName("incheon") val incheon : String,
    @SerializedName("daejeon") val daejeon : String,
    @SerializedName("gyeongbuk") val gyeongbuk : String,
    @SerializedName("sejong") val sejong : String,
    @SerializedName("gwangju") val gwangju : String,
    @SerializedName("jeonbuk") val jeonbuk : String,
    @SerializedName("gangwon") val gangwon : String,
    @SerializedName("ulsan") val ulsan : String,
    @SerializedName("jeonnam") val jeonnam : String,
    @SerializedName("seoul") val seoul : String,
    @SerializedName("busan") val busan : String,
    @SerializedName("jeju") val jeju : String,
    @SerializedName("chungbuk") val chungbuk : String,
    @SerializedName("gyeongnam") val gyeongnam : String,
    @SerializedName("dataTime") val dataTime : String,
    @SerializedName("dataGubun") val dataGubun : String,
    @SerializedName("gyeonggi") val gyeonggi : String,
    @SerializedName("itemCode") val itemCode : String,
    )
