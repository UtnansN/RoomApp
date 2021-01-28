package com.example.roomapp.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.R

class RoomFragment : Fragment() {

    private lateinit var roomViewModel: RoomViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_room, container, false)

        val txtRoomName: TextView = root.findViewById(R.id.room_name)
        roomViewModel.roomName.observe(viewLifecycleOwner, Observer {
            txtRoomName.text = requireArguments().getInt("roomId").toString()
        })

        return root
    }

}