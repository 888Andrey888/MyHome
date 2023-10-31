package com.example.myhome.presintation.notesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.myhome.R
import com.example.myhome.core.base.BaseFragment
import com.example.myhome.databinding.FragmentNotesBinding
import com.example.myhome.domain.models.Note
import com.example.myhome.presintation.notesfragment.adapter.NotesAdapter
import com.example.myhome.utils.SwipeController
import com.example.myhome.utils.Сonstants.REQUIRES_KEY_UPDATE_NOTE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment<FragmentNotesBinding>() {

    private val viewModel: NotesViewModel by viewModels()
    private val adapter = NotesAdapter()
    private lateinit var itemTouchHelper: ItemTouchHelper
    private var _notes = listOf<Note>()

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNotesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initLiveData()
        initListener()
        initSwipeController()
    }

    private fun initSwipeController() {
        itemTouchHelper = ItemTouchHelper(object : SwipeController(binding.rvNotes) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val editButton = editButton(position, _notes[position])
                val favoritesButton = deleteButton(position)
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

    private fun initLiveData() {
        viewModel.nodes.observe(viewLifecycleOwner) { notes ->
            _notes = notes
            initRecyclerView(notes)
        }
    }

    private fun initRecyclerView(notes: List<Note>) {
        binding.rvNotes.adapter = adapter
        adapter.addData(notes)
    }

    private fun deleteButton(position: Int): SwipeController.UnderlayButton {
        return SwipeController.UnderlayButton(
            requireContext(),
            "Delete",
            20f,
            R.color.grey,
            R.drawable.ic_delete,
            object : SwipeController.UnderlayButtonClickListener {
                override fun onClick() {
                    Toast.makeText(
                        requireContext(),
                        "delete clicked on position: $position",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
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

    private fun init() {
        viewModel.getAllNotes()
    }

}