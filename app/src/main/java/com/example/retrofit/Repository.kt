package com.example.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: CalculateApi) {

    fun makeRequest(firstName: String, secondName: String): LiveData<CalculateModel> {
        val liveData = MutableLiveData<CalculateModel>()

        api.getPercentage(firstName,secondName).enqueue(
            object : Callback<CalculateModel> {
                override fun onResponse(
                    call: Call<CalculateModel>,
                    response: Response<CalculateModel>
                ) {
                    liveData.postValue(response.body())
                }

                override fun onFailure(call: Call<CalculateModel>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.stackTrace}")
                }

            }
        )
        return liveData
    }
}