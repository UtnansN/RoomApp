package com.example.roomapp.ui.allrooms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.model.Room
import com.example.roomapp.ui.allrooms.viewmodel.CreateRoomViewModel

class CreateRoomFragment : Fragment() {

    private lateinit var createRoomViewModel: CreateRoomViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        createRoomViewModel =
                ViewModelProvider(this).get(CreateRoomViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_room_create, container, false)

        val roomNameEditBox = root.findViewById<EditText>(R.id.txt_create_room_name)
        val roomDescriptionEditBox = root.findViewById<EditText>(R.id.txt_create_room_description)

        val submitBtn = root.findViewById<Button>(R.id.btn_create_room)
        submitBtn.setOnClickListener {
            val room = Room(
                    id = 0,
                    name = roomNameEditBox.text.toString(),
                    description = roomDescriptionEditBox.text.toString(),
                    roomCode = "ASDF"
            )
            createRoomViewModel.addRoom(room)
            findNavController().navigate(R.id.action_navigation_room_join_create_to_navigation_my_rooms)
        }

        return root
    }

}