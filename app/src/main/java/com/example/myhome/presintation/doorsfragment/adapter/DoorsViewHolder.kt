package com.example.myhome.presintation.doorsfragment.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myhome.data.models.DoorsModel
import com.example.myhome.databinding.DoorsItemBinding
import com.example.myhome.utils.showImage

class DoorsViewHolder(private val binding: DoorsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(doorsModel: DoorsModel.Data) {
        var showCamView = false
        binding.tvCamTitle.text = doorsModel.name

        if (!doorsModel.snapshot.isNullOrEmpty())
            binding.imgCamView.load(doorsModel.snapshot)

        binding.imgCamView.showImage(showCamView)
        itemView.setOnClickListener {
            showCamView = !showCamView
            binding.imgCamView.showImage(showCamView)
            if (!showCamView)
                binding.imgFavorites.showImage(showCamView)
            else
                binding.imgFavorites.showImage(doorsModel.favorites)
        }
    }

}