package com.example.myhome.presintation.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myhome.R
import com.example.myhome.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment() : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.viewPager.adapter =
            PagerAdapter(this)
        binding.tabLayout.tabIconTint = null
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                1 -> tab.text = getString(R.string.cams)
                2 -> tab.text = getString(R.string.doors)
                0 -> tab.text = getString(R.string.notes)
            }
        }.attach()
    }
}