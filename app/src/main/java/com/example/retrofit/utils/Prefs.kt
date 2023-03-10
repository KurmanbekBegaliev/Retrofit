package com.example.retrofit.utils

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class Prefs @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val ON_BOARDING_STATE = "have seen on boarding"
    }

    private val liveData = MutableLiveData<Boolean>()

    fun setHaveSeenOnBoarding() {
        sharedPreferences.edit().putBoolean(ON_BOARDING_STATE, true).apply()
    }

    fun isHaveSeenOnBoarding(): LiveData<Boolean> {
        liveData.value = sharedPreferences.getBoolean(ON_BOARDING_STATE, false)
        return liveData
    }

}