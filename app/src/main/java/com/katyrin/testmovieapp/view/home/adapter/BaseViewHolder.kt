package com.katyrin.testmovieapp.view.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.katyrin.testmovieapp.model.data.RecyclerData

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataItem: RecyclerData)
}