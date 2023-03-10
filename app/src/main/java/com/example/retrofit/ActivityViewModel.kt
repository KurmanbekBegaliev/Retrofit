package com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.utils.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(private val repository: Repository, private val prefs: Prefs) : ViewModel() {


    fun makeRequest(firstName: String, secondName: String): LiveData<CalculateModel> {
        return repository.makeRequest(firstName, secondName)
    }

    fun setHaveSeenOnBoarding() {
        prefs.setHaveSeenOnBoarding()
    }

    fun isHaveSeenOnBoarding(): LiveData<Boolean> {
        return prefs.isHaveSeenOnBoarding()
    }
}