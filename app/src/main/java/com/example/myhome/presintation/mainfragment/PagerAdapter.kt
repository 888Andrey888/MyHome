package com.example.myhome.presintation.mainfragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myhome.presintation.camsfragment.CamsFragment
import com.example.myhome.presintation.doorsfragment.DoorsFragment
import com.example.myhome.presintation.notesfragment.NotesFragment

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CamsFragment()
            1 -> DoorsFragment()
            else -> NotesFragment()
        }
    }
}