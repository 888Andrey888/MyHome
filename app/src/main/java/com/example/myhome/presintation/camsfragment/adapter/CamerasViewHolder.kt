package com.example.myhome.presintation.camsfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myhome.data.storage.models.CamerasModelDTO
import com.example.myhome.databinding.CamItemBinding
import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.utils.showImage

class CamerasViewHolder(private val binding: CamItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(camera: CamerasModel.Data.Camera) {
        binding.imgCamView.load(camera.snapshot)
        binding.tvCamTitle.text = camera.name
        binding.imgFavorites.showImage(camera.favorites)
    }
}