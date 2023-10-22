package com.example.myhome.presintation.doorsfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.data.models.DoorsModel
import com.example.myhome.databinding.DoorsItemBinding


class DoorsAdapter() : RecyclerView.Adapter<DoorsViewHolder>() {

    private var list = mutableListOf<DoorsModel.Data>()

    fun addData(doors: List<DoorsModel.Data>) {
        list.clear()
        list.addAll(doors)
        notifyItemRangeInserted(list.size, doors.size - list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DoorsViewHolder(
        DoorsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DoorsViewHolder, position: Int) {
        holder.bind(list[position])
    }
}