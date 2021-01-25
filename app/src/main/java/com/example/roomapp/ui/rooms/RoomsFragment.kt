package com.example.roomapp.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.MainActivity
import com.example.roomapp.R
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.models.Room

class RoomsFragment : Fragment() {

    private lateinit var roomsViewModel: RoomsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        roomsViewModel =
                ViewModelProvider(this).get(RoomsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_my_rooms, container, false)

        val addRoomFab: View = root.findViewById(R.id.fab_add_room)
        addRoomFab.setOnClickListener { addRoom(it) }

        val adapter = RoomsItemAdapter()

//        val roomRecyclerView: RecyclerView = root.findViewById(R.id.rec_my_rooms)
//        val data = (activity as MainActivity).db!!.roomDao().getAll()
//        roomRecyclerView.adapter = RoomsItemAdapter(dataSet = data)

        return root
    }

    private fun addRoom(view: View) {
        AppDatabase.getInstance(requireContext()).roomDao().insert(Room(id = 0, name = "AAAA", description = "ASDF"))
    }
}