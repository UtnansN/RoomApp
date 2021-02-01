package com.example.roomapp.ui.room.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.ui.room.adapter.EventItemAdapter
import com.example.roomapp.ui.room.viewmodel.EventViewModel

class EventFragment: Fragment() {

    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventItemAdapter: EventItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        eventViewModel = ViewModelProvider(requireActivity()).get(EventViewModel::class.java)

        val roomId = requireArguments().getInt("roomId")
        eventViewModel.setRoomId(roomId)

        val root = inflater.inflate(R.layout.fragment_itemlist, container, false)

        eventItemAdapter = EventItemAdapter(EventItemAdapter.EventDiff())

        eventRecyclerView = root.findViewById(R.id.rec_items)
        eventRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = eventItemAdapter
        }

        val addEventFab: View = root.findViewById(R.id.fab_add_item)
        addEventFab.setOnClickListener {
            val bundle = bundleOf("roomId" to roomId)
            findNavController().navigate(R.id.action_navigation_room_to_navigation_create_event, bundle)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        eventViewModel.getEvents().observe(viewLifecycleOwner, {
                events -> events?.let { eventItemAdapter.submitList(it) }
        })
    }

}