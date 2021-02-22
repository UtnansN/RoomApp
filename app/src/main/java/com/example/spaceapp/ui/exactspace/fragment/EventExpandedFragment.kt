package com.example.spaceapp.ui.exactspace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.data.model.EventBriefDTO
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.databinding.FragmentEventExpandedBinding
import com.example.spaceapp.ui.exactspace.adapter.UserItemAdapter
import com.example.spaceapp.ui.exactspace.viewmodel.EventExpandedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventExpandedFragment : Fragment() {

    private lateinit var eventExpandedViewModel: EventExpandedViewModel
    private lateinit var attendeeRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        eventExpandedViewModel = ViewModelProvider(this).get(EventExpandedViewModel::class.java)

        val spaceCode = requireArguments().getString("spaceCode")!!
        val eventBrief = requireArguments().getParcelable<EventBriefDTO>("eventBrief")!!

        eventExpandedViewModel.setExpandedContext(spaceCode, eventBrief)

        val binding = FragmentEventExpandedBinding.inflate(inflater, container, false)
        binding.viewModel = eventExpandedViewModel
        binding.lifecycleOwner = this

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

        eventExpandedViewModel.eventExpandedDTO.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { extendedDTO -> itemAdapter.submitList(extendedDTO.attendees) }
                }
                else -> {}
            }
        }

        eventExpandedViewModel.attendanceCallState.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    eventExpandedViewModel.fetchAttendees()
                }
                else -> {}
            }
        }
    }

}