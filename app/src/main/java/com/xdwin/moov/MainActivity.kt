package com.xdwin.moov

import android.os.Bundle
import com.xdwin.abstraction.abstraction.BaseActivity
import com.xdwin.moov.features.home.home.HomeFragment
import com.xdwin.moov.features.home.home.HomeSwitchFragmentListener
import com.xdwin.moov.features.home.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), HomeSwitchFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultFragment()
    }

    // fragment region
    private fun setDefaultFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, HomeFragment()).commit()
    }

    override fun switchToHomeFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(currentFragment!!)
        fragmentTransaction.add(R.id.container, HomeFragment()).commit()
    }

    override fun switchToSearchFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(currentFragment!!)
        fragmentTransaction.add(R.id.container, SearchFragment()).commit()
    }
    // endregion
}