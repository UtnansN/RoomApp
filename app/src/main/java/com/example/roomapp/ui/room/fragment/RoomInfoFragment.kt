package com.example.roomapp.ui.room.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.R
import com.example.roomapp.ui.room.viewmodel.RoomInfoViewModel

class RoomInfoFragment : Fragment() {

    private lateinit var roomInfoViewModel: RoomInfoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        roomInfoViewModel = ViewModelProvider(this).get(RoomInfoViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_room_about, container, false)

        return root
    }

}