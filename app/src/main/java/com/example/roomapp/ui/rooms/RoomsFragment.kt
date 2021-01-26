package com.example.roomapp.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.MainActivity
import com.example.roomapp.R
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.models.Room

class RoomsFragment : Fragment() {

    private lateinit var roomsViewModel: RoomsViewModel
    private lateinit var roomRecyclerView: RecyclerView
    private lateinit var roomsItemAdapter: RoomsItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        roomsViewModel =
                ViewModelProvider(requireActivity()).get(RoomsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_my_rooms, container, false)

        roomsItemAdapter = RoomsItemAdapter(RoomsItemAdapter.RoomDiff())
        roomRecyclerView = root.findViewById(R.id.rec_my_rooms)
        roomRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = roomsItemAdapter
        }

        val addRoomFab: View = root.findViewById(R.id.fab_add_room)
        addRoomFab.setOnClickListener { roomsViewModel.addRoom() }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        roomsViewModel.getUserRooms().observe(viewLifecycleOwner, Observer {
            rooms -> rooms?.let { roomsItemAdapter.submitList(it) }
        })
    }

}