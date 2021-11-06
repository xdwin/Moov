package com.xdwin.moov.features.detail.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xdwin.moov.features.detail.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}