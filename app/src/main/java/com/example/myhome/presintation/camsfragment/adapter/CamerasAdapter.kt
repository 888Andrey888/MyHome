package com.example.myhome.presintation.camsfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhome.data.models.CamerasModel
import com.example.myhome.databinding.CamItemBinding

class CamerasAdapter : RecyclerView.Adapter<CamerasViewHolder>() {

    private var list = mutableListOf<CamerasModel.Data.Camera>()

    fun addData(cameras: List<CamerasModel.Data.Camera>) {
        list.clear()
        list.addAll(cameras)
        notifyItemRangeInserted(list.size, cameras.size - list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CamerasViewHolder(
        CamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CamerasViewHolder, position: Int) {
        holder.bind(camera = list[position])
    }
}