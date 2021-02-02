package com.example.spaceapp.ui.exactspace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.ui.exactspace.adapter.EventItemAdapter
import com.example.spaceapp.ui.exactspace.viewmodel.EventViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment: Fragment() {

    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventItemAdapter: EventItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        eventViewModel = ViewModelProvider(requireActivity()).get(EventViewModel::class.java)

        val spaceId = requireArguments().getInt("spaceId")
        eventViewModel.setSpaceId(spaceId)

        val root = inflater.inflate(R.layout.fragment_itemlist, container, false)

        eventItemAdapter = EventItemAdapter(EventItemAdapter.EventDiff())

        eventRecyclerView = root.findViewById(R.id.rec_items)
        eventRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = eventItemAdapter
        }

        val addEventFab: View = root.findViewById(R.id.fab_add_item)
        addEventFab.setOnClickListener {
            val bundle = bundleOf("spaceId" to spaceId)
            findNavController().navigate(R.id.action_navigation_space_to_create_event, bundle)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        eventViewModel.allEvents.observe(viewLifecycleOwner, {
                events -> events?.let { eventItemAdapter.submitList(it) }
        })
    }

}