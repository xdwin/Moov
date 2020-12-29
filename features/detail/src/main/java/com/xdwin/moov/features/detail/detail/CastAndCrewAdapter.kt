package com.xdwin.moov.features.detail.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xdwin.abstraction.adapter.BaseViewHolder
import com.xdwin.data.URLS
import com.xdwin.data.data.Cast
import com.xdwin.moov.features.detail.R
import kotlinx.android.synthetic.main.item_cast_and_crew.view.*

class CastAndCrewAdapter(val casts: List<Cast>) : RecyclerView.Adapter<CastAndCrewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cast_and_crew, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return casts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(casts[position], {})
    }

    class ViewHolder(view: View) : BaseViewHolder<Cast>(view, 0) {
        val castPhoto = view.ivCastPhoto
        val castName = view.tvCastName
        override fun bind(data: Cast, onClickListener: (Cast) -> Unit) {
            Glide.with(castPhoto)
                .load(URLS.BASE_IMAGE_MOVIEDB_500 + data.profile_path)
                .into(castPhoto)
            castName.text = data.name
        }
    }
}