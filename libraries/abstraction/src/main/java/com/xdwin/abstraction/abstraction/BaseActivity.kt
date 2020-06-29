package com.xdwin.abstraction.abstraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xdwin.abstraction.R
import com.xdwin.abstraction.jumper.Jumper

class BaseActivity() : AppCompatActivity(R.layout.activity_container) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentId = intent.getStringExtra(Jumper.FRAGMENT_ID_KEY)
        val fragment = Jumper.mapFragment[fragmentId]
        fragment?.run {
            inflateFragment(this)
        }
    }

    private fun inflateFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}