package com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {

    private val repository = Repository()
//    private var test = 5

    fun makeRequest(firstName: String, secondName: String): LiveData<CalculateModel> {
        return repository.makeRequest(firstName, secondName)
    }

//    fun getTest() = test
//
//    fun setTest(test: Int) {
//        this.test = test
//    }
}