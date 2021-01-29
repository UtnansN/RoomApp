package com.example.roomapp.ui.allrooms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.R
import com.example.roomapp.ui.allrooms.viewmodel.JoinRoomViewModel

class JoinRoomFragment : Fragment() {

    private lateinit var joinRoomViewModel: JoinRoomViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        joinRoomViewModel =
                ViewModelProvider(this).get(JoinRoomViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_room_join, container, false)

        requireActivity().title = "Join a room"

        return root
    }

}