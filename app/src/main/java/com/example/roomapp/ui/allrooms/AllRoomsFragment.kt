package com.example.roomapp.ui.allrooms

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

class AllRoomsFragment : Fragment() {

    private lateinit var allRoomsViewModel: AllRoomsViewModel
    private lateinit var roomRecyclerView: RecyclerView
    private lateinit var allRoomsItemAdapter: AllRoomsItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        allRoomsViewModel =
                ViewModelProvider(requireActivity()).get(AllRoomsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_my_rooms, container, false)

        allRoomsItemAdapter = AllRoomsItemAdapter(AllRoomsItemAdapter.RoomDiff())
        roomRecyclerView = root.findViewById(R.id.rec_my_rooms)
        roomRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = allRoomsItemAdapter
        }

        val addRoomFab: View = root.findViewById(R.id.fab_add_room)
        addRoomFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_my_rooms_to_createRoomFragment)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        allRoomsViewModel.getUserRooms().observe(viewLifecycleOwner, Observer {
            rooms -> rooms?.let { allRoomsItemAdapter.submitList(it) }
        })
    }

}