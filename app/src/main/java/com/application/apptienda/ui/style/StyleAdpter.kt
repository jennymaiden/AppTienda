package com.application.apptienda.ui.style

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.apptienda.R
import com.application.apptienda.databinding.DesingItemBinding
import com.application.apptienda.ui.desing.onClickListener
import com.application.apptienda.utils.Urls
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class StyleAdpter(private val styleList: List<Style>, private val listener: onClickStyle): RecyclerView.Adapter<StyleAdpter.StyleViewHolder>() {

    private lateinit var context: Context

    inner class StyleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = DesingItemBinding.bind(itemView)
        fun setListener(desing: Style, position: Int) {

            binding.root.setOnClickListener {
                listener.onClick(desing, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.desing_item, parent, false)
        return StyleViewHolder(view)

    }

    override fun onBindViewHolder(holder: StyleViewHolder, position: Int) {
        val desing = styleList.get(position)
        with(holder) {
            setListener(desing, position)

            Glide.with(context).load(desing.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imageItem)

            binding.txtItem.text = desing.desingName
        }
    }

    override fun getItemCount(): Int {
        return styleList.size
    }
}