package com.example.spaceapp.ui.userspaces.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.data.model.dto.Resource
import com.example.spaceapp.ui.userspaces.adapter.AllSpacesItemAdapter
import com.example.spaceapp.ui.userspaces.viewmodel.AllSpacesViewModel
import com.example.spaceapp.utils.DateTimeConverter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllSpacesFragment : Fragment() {

    private lateinit var viewModel: AllSpacesViewModel
    private lateinit var spaceRecyclerView: RecyclerView
    private lateinit var allSpacesItemAdapter: AllSpacesItemAdapter

    @Inject
    lateinit var dateTimeConverter: DateTimeConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.menu_add_or_join_space).isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add_or_join_space -> findNavController()
                .navigate(R.id.action_navigation_my_spaces_to_space_join_or_create)
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(requireActivity()).get(AllSpacesViewModel::class.java)
        viewModel.invokeSpaceUpdate()

        val root = inflater.inflate(R.layout.fragment_itemlist, container, false)

        allSpacesItemAdapter = AllSpacesItemAdapter(AllSpacesItemAdapter.SpaceDiff(), dateTimeConverter) {
            val action = AllSpacesFragmentDirections.actionNavigationMySpacesToSpecificSpace(it.name, it.code)
            findNavController().navigate(action)
        }

        spaceRecyclerView = root.findViewById(R.id.rec_items)
        spaceRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = allSpacesItemAdapter
        }

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