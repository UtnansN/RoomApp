package com.example.roomapp.ui.allrooms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.roomapp.R
import com.example.roomapp.ui.allrooms.adapter.CreateOrJoinViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CreateOrJoinFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_tabpager, container, false)

        tabLayout = root.findViewById(R.id.tabpager_tabs)
        viewPager = root.findViewById(R.id.tabpager_pager)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter =
            CreateOrJoinViewPagerAdapter(
                this
            )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.join_a_room)
                1 -> tab.text = resources.getString(R.string.create_a_room)
            }
        }.attach()
    }

}