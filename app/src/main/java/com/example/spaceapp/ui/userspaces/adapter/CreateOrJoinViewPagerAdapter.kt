package com.example.spaceapp.ui.userspaces.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spaceapp.ui.userspaces.fragment.CreateSpaceFragment
import com.example.spaceapp.ui.userspaces.fragment.JoinSpaceFragment
import java.lang.IllegalStateException

class CreateOrJoinViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> JoinSpaceFragment()
        1 -> CreateSpaceFragment()
        else -> throw IllegalStateException("Invalid adapter position")
    }


}