package com.xdwin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xdwin.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateMovieFragment()
    }

    private fun inflateMovieFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, HomeFragment())
        transaction.commit()
    }

}