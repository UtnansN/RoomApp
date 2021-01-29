package com.example.roomapp.ui.room.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.roomapp.ui.room.fragment.DiscussionFragment
import com.example.roomapp.ui.room.fragment.EventFragment
import java.lang.IllegalStateException

class RoomViewPagerAdapter(fragment: Fragment, private val roomId: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> EventFragment()
            1 -> DiscussionFragment()
            else -> throw IllegalStateException("Invalid adapter position")
        }
        val args = Bundle()
        args.putInt("roomId", roomId)
        fragment.arguments = args
        return fragment
    }


}