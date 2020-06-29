package com.xdwin.abstraction.jumper

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.xdwin.abstraction.abstraction.BaseActivity
import java.util.*

/**
 * A navigation helper class
 * You should always use this whenever you want to jump to a fragment
 * that use the BaseActivity as a Style
 */
object Jumper {
    const val FRAGMENT_ID_KEY = "fragment_id"
    val mapFragment: MutableMap<String, Fragment> = mutableMapOf()

    fun jump(context: Context, fragment: Fragment) {
        val isSamePackage = isSamePackage(context, fragment)
        val intent: Intent = if (isSamePackage) {
            Intent(context, BaseActivity::class.java)
        } else {
            Intent(context, BaseActivity::class.java).apply {
                putExtra(FRAGMENT_ID_KEY, generateId(fragment))
            }
        }
        context.startActivity(intent)
    }

//    fun jumpFeature(context, )

    private fun isSamePackage(context: Context, fragment: Fragment): Boolean {
        val ctxPkg = context.javaClass.`package`
        val pkg = fragment.javaClass.`package`
        return ctxPkg == pkg
    }

    private fun generateId(fragment: Fragment): String {
        val id = UUID.randomUUID().toString()
        putFragment(id, fragment)
        return id
    }

    private fun putFragment(id: String, fragment: Fragment) {
        mapFragment[id] = fragment
    }
}