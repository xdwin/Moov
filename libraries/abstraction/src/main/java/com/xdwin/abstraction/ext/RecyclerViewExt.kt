package com.xdwin.abstraction.ext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <T> RecyclerView.setupVerticalAdapter(adapter: T) {
    this.adapter = adapter as RecyclerView.Adapter<*>
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}

fun <T> RecyclerView.setupHorizontalAdapter(adapter: T) {
    this.adapter = adapter as RecyclerView.Adapter<*>
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun <T> RecyclerView.setupGridAdapter(adapter: T, column: Int) {
    this.adapter = adapter as RecyclerView.Adapter<*>
    this.layoutManager = GridLayoutManager(context, column)
}