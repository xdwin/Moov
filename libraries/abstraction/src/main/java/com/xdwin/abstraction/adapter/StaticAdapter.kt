package com.xdwin.abstraction.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xdwin.abstraction.R

class StaticAdapter(
    val viewId: Int,
    val onClickListener: (() -> Unit)? = null
) : RecyclerView.Adapter<StaticAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewId, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onClickListener)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(onClickListener: (() -> Unit)?) {
            view.setOnClickListener { onClickListener?.invoke() }
        }
    }
}