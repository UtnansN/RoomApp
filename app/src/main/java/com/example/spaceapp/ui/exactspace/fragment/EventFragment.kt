package com.example.spaceapp.ui.exactspace.fragment

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.data.model.remote.Resource
import com.example.spaceapp.ui.exactspace.adapter.EventItemAdapter
import com.example.spaceapp.ui.exactspace.viewmodel.EventViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment() {

    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventItemAdapter: EventItemAdapter
    private lateinit var emptyView: LinearLayout

    private lateinit var spaceCode: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        eventViewModel = ViewModelProvider(requireParentFragment()).get(EventViewModel::class.java)

        spaceCode = requireArguments().getString("spaceCode")!!
        eventViewModel.setSpaceCode(spaceCode)

        val root = inflater.inflate(R.layout.fragment_itemlist, container, false)

        eventItemAdapter = EventItemAdapter(EventItemAdapter.EventDiff())

        eventRecyclerView = root.findViewById(R.id.rec_items)
        eventRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = eventItemAdapter
        }

        emptyView = root.findViewById(R.id.rec_emptyview)

//        val addEventFab: View = root.findViewById(R.id.fab_add_item)
//        addEventFab.setOnClickListener {
//            val bundle = bundleOf("spaceCode" to spaceCode)
//            findNavController().navigate(R.id.action_navigation_space_to_create_event, bundle)
//        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        eventViewModel.allEvents.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null && it.data.isNotEmpty()) {
                        emptyView.visibility = View.GONE
                        it.data.let { events -> eventItemAdapter.submitList(events) }
                    }
                    else {
                        emptyView.visibility = View.VISIBLE
                    }
                }
                Resource.Status.ERROR -> Toast.makeText(activity, it.message, Toast.LENGTH_SHORT)
                    .show()
                Resource.Status.LOADING -> {
                }
            }

        })
    }

}