package com.example.roomapp.ui.allrooms.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.roomapp.ui.allrooms.fragment.CreateRoomFragment
import com.example.roomapp.ui.allrooms.fragment.JoinRoomFragment
import java.lang.IllegalStateException

class CreateOrJoinViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> JoinRoomFragment()
        1 -> CreateRoomFragment()
        else -> throw IllegalStateException("Invalid adapter position")
    }


}