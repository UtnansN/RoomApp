package com.example.spaceapp.ui.userspaces.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.data.model.remote.Resource
import com.example.spaceapp.ui.userspaces.adapter.AllSpacesItemAdapter
import com.example.spaceapp.ui.userspaces.viewmodel.AllSpacesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllSpacesFragment : Fragment() {

    private lateinit var viewModel: AllSpacesViewModel
    private lateinit var spaceRecyclerView: RecyclerView
    private lateinit var allSpacesItemAdapter: AllSpacesItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(requireActivity()).get(AllSpacesViewModel::class.java)
        viewModel.invokeSpaceUpdate()

        val root = inflater.inflate(R.layout.fragment_itemlist, container, false)

        allSpacesItemAdapter = AllSpacesItemAdapter(AllSpacesItemAdapter.SpaceDiff()) {
            val action = AllSpacesFragmentDirections.actionNavigationMySpacesToSpecificSpace(it.code)
            findNavController().navigate(action)
        }

        spaceRecyclerView = root.findViewById(R.id.rec_items)
        spaceRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = allSpacesItemAdapter
        }

        val addRoomFab: View = root.findViewById(R.id.fab_add_item)
        addRoomFab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_my_spaces_to_space_join_or_create)
        }

        requireActivity().title = "My Spaces"

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.allSpaces.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { spaces -> allSpacesItemAdapter.submitList(spaces) }
                }
                Resource.Status.ERROR -> Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING -> {}
            }
        })
    }

}