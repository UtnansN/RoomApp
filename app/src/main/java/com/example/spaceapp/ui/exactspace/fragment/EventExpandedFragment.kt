package com.example.spaceapp.ui.exactspace.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.databinding.FragmentEventExpandedBinding
import com.example.spaceapp.ui.exactspace.adapter.UserItemAdapter
import com.example.spaceapp.ui.exactspace.viewmodel.EventExpandedViewModel
import com.google.android.material.transition.MaterialContainerTransform

class EventExpandedFragment : Fragment() {

    private lateinit var eventExpandedViewModel: EventExpandedViewModel
    private lateinit var attendeeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition = MaterialContainerTransform().apply {
//            drawingViewId = R.id.nav_host_fragment
//            scrimColor = Color.TRANSPARENT
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        eventExpandedViewModel = ViewModelProvider(this).get(EventExpandedViewModel::class.java)

        val binding = FragmentEventExpandedBinding.inflate(inflater, container, false)
        binding.viewModel = eventExpandedViewModel

        val root = binding.root

        attendeeRecyclerView = root.findViewById(R.id.rec_event_expanded_attendees)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemAdapter = UserItemAdapter(UserItemAdapter.UserDiff())

        attendeeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = itemAdapter
        }

        eventExpandedViewModel.attendees.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {}
                Resource.Status.SUCCESS -> {
                    it.data?.let { userList -> itemAdapter.submitList(userList) }
                }
                Resource.Status.ERROR -> {}
            }
        }
    }

}