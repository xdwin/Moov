package com.xdwin.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

abstract class BaseViewHolder<T : Serializable>(itemView: View, val tag: Int) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}