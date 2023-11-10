package com.example.myhome.presintation.notesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.myhome.R
import com.example.myhome.core.base.BaseFragment
import com.example.myhome.databinding.FragmentNotesBinding
import com.example.myhome.domain.models.Note
import com.example.myhome.presintation.notesfragment.adapter.NotesAdapter
import com.example.myhome.utils.State
import com.example.myhome.utils.SwipeController
import com.example.myhome.utils.Сonstants.REQUIRES_KEY_UPDATE_NOTE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment(
) : BaseFragment<FragmentNotesBinding>() {

    private val viewModel: NotesViewModel by viewModels()
    private val adapter = NotesAdapter(this::setDone)
    private lateinit var itemTouchHelper: ItemTouchHelper
    private var _notes = listOf<Note>()

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNotesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initFlow()
        initListener()
        initSwipeController()
    }

    private fun initSwipeController() {
        itemTouchHelper = ItemTouchHelper(object : SwipeController(binding.rvNotes) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val editButton = editButton(position, _notes[position])
                val favoritesButton = deleteButton(position, _notes[position])
                return listOf(editButton, favoritesButton)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvNotes)
    }

    private fun initListener() {
        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.setFragment)
        }
    }

    private fun initFlow() {
        viewModel.viewState.onEach {
            when (it) {
                is State.Loading -> {
                    binding.shimmerLayout.startShimmer()
                    binding.shimmerLayout.visibility = View.VISIBLE
                }

                is State.Success -> {
                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.visibility = View.GONE
                    initRecyclerView(it.data!!)
                    _notes = it.data!!
                }

                is State.Empty -> {
                    Toast.makeText(requireContext(), "Data is empty", Toast.LENGTH_SHORT).show()
                }

                is State.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initRecyclerView(notes: List<Note>) {
        binding.rvNotes.adapter = adapter
        adapter.addData(notes)
    }

    private fun deleteButton(position: Int, note: Note): SwipeController.UnderlayButton {
        return SwipeController.UnderlayButton(
            requireContext(),
            "Delete",
            20f,
            R.color.grey,
            R.drawable.ic_delete,
            object : SwipeController.UnderlayButtonClickListener {
                override fun onClick() {
                    showAlertDialog(note)
                }
            }
        )
    }

    private fun showAlertDialog(note: Note) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle(getString(R.string.delete))
            .setMessage(getString(R.string.delete_message)).setCancelable(true)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.deleteNote(note)
            }.setNegativeButton(getString(R.string.no)) { _, _ -> }.show()
    }

    private fun editButton(position: Int, note: Note): SwipeController.UnderlayButton {
        return SwipeController.UnderlayButton(
            requireContext(),
            "Edit",
            20f,
            R.color.grey,
            R.drawable.ic_edit,
            object : SwipeController.UnderlayButtonClickListener {
                override fun onClick() {
                    findNavController().navigate(
                        R.id.setFragment,
                        bundleOf(REQUIRES_KEY_UPDATE_NOTE to note)
                    )
                }
            }
        )
    }

    private fun setDone(note: Note) {
        viewModel.setDone(note)
    }

    private fun init() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getAllNotes()
        }
    }

}