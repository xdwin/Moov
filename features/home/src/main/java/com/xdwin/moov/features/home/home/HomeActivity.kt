package com.xdwin.moov.features.home.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xdwin.moov.features.home.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}