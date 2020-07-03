package com.xdwin.base

import android.os.Bundle
import com.xdwin.abstraction.abstraction.BaseActivity
import com.xdwin.home.home.HomeFragment
import com.xdwin.home.home.HomeSwitchFragmentListener
import com.xdwin.home.search.SearchFragment

class MainActivity : BaseActivity(), HomeSwitchFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultFragment()
    }

    // fragment region
    private fun setDefaultFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(com.xdwin.home.R.id.container, HomeFragment()).commit()
    }

    override fun switchToHomeFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(currentFragment!!)
        fragmentTransaction.add(com.xdwin.home.R.id.container, HomeFragment()).commit()
    }

    override fun switchToSearchFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(currentFragment!!)
        fragmentTransaction.add(com.xdwin.home.R.id.container, SearchFragment()).commit()
    }
    // endregion
}