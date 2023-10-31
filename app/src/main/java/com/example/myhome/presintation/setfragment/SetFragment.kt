package com.example.myhome.presintation.setfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myhome.R
import com.example.myhome.core.base.BaseFragment
import com.example.myhome.databinding.FragmentSetBinding
import com.example.myhome.domain.models.Note
import com.example.myhome.utils.EditTextEmptyLineException
import com.example.myhome.utils.checkingForEmptyLine
import com.example.myhome.utils.Сonstants.REQUIRES_KEY_UPDATE_NOTE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetFragment : BaseFragment<FragmentSetBinding>() {

    private val viewModel: SetViewModel by viewModels()
    private var _note: Note? = null

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSetBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _note = arguments?.getSerializable(REQUIRES_KEY_UPDATE_NOTE) as Note?

        initFields()
        initListener()
    }

    private fun initListener() {
        binding.btnAddNote.setOnClickListener {
            try {
                if (_note == null) {
                    insertNote()
                } else {
                    updateNote()
                }
                findNavController().navigateUp()
            } catch (e: EditTextEmptyLineException) {
                binding.etTitle.requestFocus()
                binding.etTitle.error = e.message
            }
        }
    }

    private fun insertNote() {
        viewModel.insertNote(
            Note(
                title = binding.etTitle.checkingForEmptyLine(),
                description = binding.etDescription.text.toString()
            )
        )
    }

    private fun updateNote() {
        val data = _note?.copy(
            title = binding.etTitle.checkingForEmptyLine(),
            description = binding.etDescription.text.toString()
        )
        data?.let { note -> viewModel.updateNote(note) }
    }

    private fun initFields() {
        if (_note != null) {
            binding.etTitle.setText(_note!!.title)
            binding.etDescription.setText(_note!!.description)
            binding.btnAddNote.text = getString(R.string.update_note)
        }
    }

}