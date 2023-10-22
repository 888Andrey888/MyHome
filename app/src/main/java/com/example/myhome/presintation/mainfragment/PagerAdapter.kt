package com.example.myhome.presintation.mainfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myhome.presintation.camsfragment.CamsFragment
import com.example.myhome.presintation.doorsfragment.DoorsFragment

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CamsFragment()
            else -> DoorsFragment()
        }
    }
}