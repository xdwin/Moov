package com.xdwin.abstraction.abstraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xdwin.abstraction.R
import com.xdwin.abstraction.listener.BackPressedListener
import com.xdwin.abstraction.jumper.Jumper

open class BaseActivity() : AppCompatActivity(R.layout.activity_container) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentId = intent.getStringExtra(Jumper.FRAGMENT_ID_KEY)
        val fragment = Jumper.mapFragment[fragmentId]
        fragment?.run {
            inflateFragment(this)
        }
    }

    override fun onDestroy() {
        onBackPressedListener = null
        super.onDestroy()
    }

    private fun inflateFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    var onBackPressedListener: BackPressedListener? = null

    override fun onBackPressed() {
        onBackPressedListener?.onBackPressed() ?: super.onBackPressed()
    }
}