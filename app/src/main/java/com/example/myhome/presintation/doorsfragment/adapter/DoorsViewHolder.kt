package com.example.myhome.presintation.doorsfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myhome.databinding.DoorsItemBinding
import com.example.myhome.utils.showImage

class DoorsViewHolder(private val binding: DoorsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(doorsModelDTO: com.example.myhome.domain.models.DoorsModel.Data) {
        var showCamView = false
        binding.tvCamTitle.text = doorsModelDTO.name

        if (!doorsModelDTO.snapshot.isNullOrEmpty())
            binding.imgCamView.load(doorsModelDTO.snapshot)

        binding.imgCamView.showImage(showCamView)
        itemView.setOnClickListener {
            showCamView = !showCamView
            binding.imgCamView.showImage(showCamView)
            if (!showCamView)
                binding.imgFavorites.showImage(showCamView)
            else
                binding.imgFavorites.showImage(doorsModelDTO.favorites)
        }
    }

}