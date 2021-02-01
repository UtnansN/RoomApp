package com.example.roomapp.ui.room.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentRoomBinding
import com.example.roomapp.ui.room.viewmodel.RoomViewModel
import com.example.roomapp.ui.room.adapter.RoomViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {

    private lateinit var roomViewModel: RoomViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val roomId = requireArguments().getInt("roomId")
        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        roomViewModel.setRoomId(roomId)

        val binding: FragmentRoomBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room, container, false)
        val root = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = roomViewModel

        val roomInfoButton: ImageButton = root.findViewById(R.id.btn_room_about)
        roomInfoButton.setOnClickListener {
            findNavController().navigate(RoomFragmentDirections.actionNavigationRoomToRoomInfoFragment())
        }

        tabLayout = root.findViewById(R.id.tabpager_tabs)
        viewPager = root.findViewById(R.id.tabpager_pager)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter =
            RoomViewPagerAdapter(this, requireArguments().getInt("roomId"))
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.events)
                1 -> tab.text = resources.getString(R.string.discussions)
            }
        }.attach()
    }


}