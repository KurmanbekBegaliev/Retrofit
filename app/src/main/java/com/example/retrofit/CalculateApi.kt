package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CalculateApi {

    @GET("getPercentage")
    fun getPercentage(
        @Query("sname") secondName : String,
        @Query("fname") firstName : String,
        @Header("X-RapidAPI-Key") apiKey : String = "df143a0f1emsh80b9985525cb8cfp1fed7cjsn5a3898e5c768"
    ): Call<CalculateModel>
}