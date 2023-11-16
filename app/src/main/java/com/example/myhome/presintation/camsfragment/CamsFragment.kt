package com.example.myhome.presintation.camsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.myhome.R
import com.example.myhome.core.base.BaseFragment
import com.example.myhome.databinding.FragmentCamsBinding
import com.example.myhome.domain.models.CamerasModel
import com.example.myhome.presintation.camsfragment.adapter.CamerasAdapter
import com.example.myhome.utils.State
import com.example.myhome.utils.SwipeController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CamsFragment : BaseFragment<FragmentCamsBinding>() {

    private val viewModel: CamerasViewModel by viewModels()
    private val adapter = CamerasAdapter()
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCamsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initLiveData()
        initSwipeController()
    }

    private fun initSwipeController() {
        itemTouchHelper = ItemTouchHelper(object : SwipeController(binding.rvCams) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val favoritesButton = favoritesButton(position)
                return listOf(favoritesButton)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvCams)
    }

    private fun init() {
        viewLifecycleOwner.lifecycleScope.launch { viewModel.getCameras() }

    }

    private fun initLiveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect {
                when (it) {
                    is State.Loading -> {
                        binding.shimmerLayout.startShimmer()
                        binding.shimmerLayout.visibility = View.VISIBLE
                    }

                    is State.Empty -> {
                        Toast.makeText(requireContext(), "Data is empty", Toast.LENGTH_SHORT).show()
                    }

                    is State.Success -> {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        initRecyclerView(it.data?.data!!.cameras)
                    }

                    is State.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initRecyclerView(cameras: List<CamerasModel.Data.Camera>) {
        adapter.addData(cameras = cameras)
        binding.rvCams.adapter = adapter

        binding.rvCams.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
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