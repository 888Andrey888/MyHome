package com.example.myhome.presintation.doorsfragment

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
import com.example.myhome.databinding.FragmentDoorsBinding
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.presintation.doorsfragment.adapter.DoorsAdapter
import com.example.myhome.utils.State
import com.example.myhome.utils.SwipeController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoorsFragment : BaseFragment<FragmentDoorsBinding>() {

    private val viewModel: DoorsViewModel by viewModels()
    private val adapter = DoorsAdapter()
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDoorsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initLiveData()
        initSwipeController()
    }

    private fun initSwipeController() {
        itemTouchHelper = ItemTouchHelper(object : SwipeController(binding.rvDoors) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val editButton = editButton(position)
                val favoritesButton = favoritesButton(position)
                return listOf(editButton, favoritesButton)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvDoors)
    }

    private fun initLiveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect{
                when(it){
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
                        initRecyclerView(it.data?.data!!)
                    }
                    is State.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initRecyclerView(doors: List<DoorsModel.Data>) {
        adapter.addData(doors)
        binding.rvDoors.adapter = adapter

        binding.rvDoors.addItemDecoration(
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

    private fun editButton(position: Int): SwipeController.UnderlayButton {
        return SwipeController.UnderlayButton(
            requireContext(),
            "Edit",
            20f,
            R.color.grey,
            R.drawable.ic_edit,
            object : SwipeController.UnderlayButtonClickListener {
                override fun onClick() {
                    Toast.makeText(
                        requireContext(),
                        "edit clicked on position: $position",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    private fun init() {
        viewLifecycleOwner.lifecycleScope.launch { viewModel.getDoors() }

    }

}