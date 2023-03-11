package com.example.retrofit.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.retrofit.ActivityViewModel
import com.example.retrofit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by lazy {
        ViewModelProvider(this)[ActivityViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.isHaveSeenOnBoarding().observe(this) {
            if (!it) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.onBoardFragment)
            }
        }

    }

}