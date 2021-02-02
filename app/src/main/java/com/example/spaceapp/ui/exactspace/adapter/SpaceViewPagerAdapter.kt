package com.example.spaceapp.ui.exactspace.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spaceapp.ui.exactspace.fragment.DiscussionFragment
import com.example.spaceapp.ui.exactspace.fragment.EventFragment
import java.lang.IllegalStateException

class SpaceViewPagerAdapter(fragment: Fragment, private val spaceCode: String) : FragmentStateAdapter(fragment) {

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
        args.putString("spaceCode", spaceCode)
        fragment.arguments = args
        return fragment
    }


}