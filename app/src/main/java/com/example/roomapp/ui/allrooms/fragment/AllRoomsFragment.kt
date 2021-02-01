package com.example.roomapp.ui.allrooms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.ui.allrooms.adapter.AllRoomsItemAdapter
import com.example.roomapp.ui.allrooms.viewmodel.AllRoomsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllRoomsFragment : Fragment() {

    private lateinit var viewModel: AllRoomsViewModel
    private lateinit var roomRecyclerView: RecyclerView
    private lateinit var allRoomsItemAdapter: AllRoomsItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(requireActivity()).get(AllRoomsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_itemlist, container, false)

        allRoomsItemAdapter = AllRoomsItemAdapter(AllRoomsItemAdapter.RoomDiff()) {
            val action = AllRoomsFragmentDirections.actionNavigationMyRoomsToSpecificRoom(it.id)
            findNavController().navigate(action)
        }

        roomRecyclerView = root.findViewById(R.id.rec_items)
        roomRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = allRoomsItemAdapter
        }

        val addRoomFab: View = root.findViewById(R.id.fab_add_item)
        addRoomFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_my_rooms_to_createJoinFragment)
        }

        requireActivity().title = "My Rooms"

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.allRooms.observe(viewLifecycleOwner, { rooms ->
            rooms?.let { allRoomsItemAdapter.submitList(it) }
        })
    }

}