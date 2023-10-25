package com.example.myhome.presintation.doorsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.myhome.R
import com.example.myhome.core.base.BaseFragment
import com.example.myhome.data.network.RetrofitClient
import com.example.myhome.data.repository.RetrofitRepositoryImpl
import com.example.myhome.data.storage.RetrofitStorageImpl
import com.example.myhome.databinding.FragmentDoorsBinding
import com.example.myhome.domain.models.DoorsModel
import com.example.myhome.domain.usecase.GetDoorsUseCase
import com.example.myhome.presintation.doorsfragment.adapter.DoorsAdapter
import com.example.myhome.utils.SwipeController

class DoorsFragment : BaseFragment<FragmentDoorsBinding>() {

    private val retrofitRepository = RetrofitRepositoryImpl(
        RetrofitStorageImpl(RetrofitClient().createApiService())
    )
    private val getDoorsUseCase = GetDoorsUseCase(retrofitRepository)

    private val viewModel = DoorsViewModel(getDoorsUseCase)
    private val adapter = DoorsAdapter()
    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDoorsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initLiveData()
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

        viewModel.doors.observe(viewLifecycleOwner) { doors ->
            initRecyclerView(doors.data)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
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

        val itemTouchHelper = ItemTouchHelper(object : SwipeController(binding.rvDoors) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val editButton = editButton(position)
                val favoritesButton = favoritesButton(position)
                return listOf(editButton, favoritesButton)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvDoors)
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
        viewModel.getDoors()
    }

}