package com.application.apptienda.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.apptienda.R
import com.application.apptienda.databinding.DesingItemBinding
import com.application.apptienda.ui.style.Style
import com.application.apptienda.ui.style.onClickStyle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ProfileAdapter(private val styleList: List<Style>, private val listener: onClickStyle) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){

    private lateinit var context: Context

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val binding = DesingItemBinding.bind(itemView)

        fun setListener(style: Style, position: Int) {

            binding.root.setOnClickListener {
                listener.onClick(style, position+1)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.desing_item, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return styleList.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val desing = styleList.get(position)
        with(holder) {
            setListener(desing, position)
            //val url = Urls::class.java.getField(desing.url).get(null) as String
            Glide.with(context).load(desing.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imageItem)

            binding.txtItem.text = desing.desingName
        }
    }
}