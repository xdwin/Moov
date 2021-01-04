package com.xdwin.abstraction.abstraction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment(contentView: Int) : Fragment(contentView), CoroutineScope {

    val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    abstract fun initDependency()
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependency()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
}