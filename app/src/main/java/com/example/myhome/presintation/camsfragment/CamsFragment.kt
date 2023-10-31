package com.example.myhome.presintation.camsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.myhome.R
import com.example.myhome.core.base.BaseFragment
import com.example.myhome.databinding.FragmentCamsBinding
import com.example.myhome.presintation.camsfragment.adapter.CamerasAdapter
import com.example.myhome.utils.SwipeController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CamsFragment : BaseFragment<FragmentCamsBinding>() {

    private val viewModel: CamerasViewModel by viewModels()
    private val adapter = CamerasAdapter()
    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCamsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initLiveData()
    }

    private fun init() {
        viewModel.getCameras()
    }

    private fun initLiveData() {
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.shimmerLayout.startShimmer()
                binding.shimmerLayout.visibility = View.VISIBLE
            } else {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            }
        }
        viewModel.cameras.observe(viewLifecycleOwner) { cameras ->
            initRecyclerView(cameras.data.cameras)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecyclerView(cameras: List<com.example.myhome.domain.models.CamerasModel.Data.Camera>) {
        adapter.addData(cameras = cameras)
        binding.rvCams.adapter = adapter

        binding.rvCams.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        val itemTouchHelper = ItemTouchHelper(object : SwipeController(binding.rvCams) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val favoritesButton = favoritesButton(position)
                return listOf(favoritesButton)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvCams)
    }

    private fun favoritesButton(position: Int): SwipeController.UnderlayButton {
        return SwipeController.UnderlayButton(
            requireContext(),
            "Favor",
            20f,
            R.color.grey,
            R.drawable.ic_star,
            object : SwipeController.UnderlayButtonClickListener {
                override fun onClick() {
                    Toast.makeText(
                        requireContext(),
                        "favorite clicked on position: $position",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}