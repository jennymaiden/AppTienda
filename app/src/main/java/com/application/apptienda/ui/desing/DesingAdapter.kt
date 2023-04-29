package com.application.apptienda.ui.desing

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.apptienda.R
import com.application.apptienda.databinding.DesingItemBinding

class DesingAdapter(private val desingList : List<Desing>, private val listener: onClickListener) : RecyclerView.Adapter<DesingAdapter.DesingViewHolder>() {

    private lateinit var context: Context

    inner class DesingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val binding = DesingItemBinding.bind(itemView)

        fun setListener(desing: Desing, position: Int) {

            binding.root.setOnClickListener {
                Log.i("CARO", "desde el adaptador")
                listener.onClick(desing, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesingViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.desing_item, parent, false)
        return DesingViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesingViewHolder, position: Int) {
        val desing = desingList.get(position)
        with(holder) {
            setListener(desing, position)
            binding.imageItem.setImageResource(desing.desingImage)
            binding.txtItem.text = desing.desingName
        }

    }

    override fun getItemCount(): Int {
        return desingList.size
    }
}