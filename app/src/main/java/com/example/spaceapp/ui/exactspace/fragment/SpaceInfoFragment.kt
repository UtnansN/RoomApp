package com.example.spaceapp.ui.exactspace.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import com.example.spaceapp.R
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.databinding.FragmentSpaceAboutBinding
import com.example.spaceapp.generated.callback.OnClickListener
import com.example.spaceapp.ui.exactspace.adapter.UserItemAdapter
import com.example.spaceapp.ui.exactspace.viewmodel.SpaceInfoViewModel
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.NonCancellable.cancel

@AndroidEntryPoint
class SpaceInfoFragment : Fragment() {

    private lateinit var spaceInfoViewModel: SpaceInfoViewModel
    private lateinit var memberRecyclerView: RecyclerView
    private lateinit var memberItemAdapter: UserItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_leave_space -> {
                val title = resources.getString(R.string.confirmation)
                val message = resources.getString(R.string.leave_space_dialog_message)
                createConfirmationDialog(title, message) { _, _ -> spaceInfoViewModel.leaveSpace() }
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.menu_leave_space).isVisible = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        spaceInfoViewModel = ViewModelProvider(this).get(SpaceInfoViewModel::class.java)

        val binding = FragmentSpaceAboutBinding.inflate(inflater, container, false)
        spaceInfoViewModel.spaceCode = requireArguments().getString("spaceCode", null)!!

        spaceInfoViewModel.getSpaceInfo()

        val root = binding.root

        binding.viewModel = spaceInfoViewModel
        binding.lifecycleOwner = this

        memberRecyclerView = root.findViewById(R.id.rec_items)
        memberItemAdapter = UserItemAdapter(UserItemAdapter.UserDiff())

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memberRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = memberItemAdapter
        }

        spaceInfoViewModel.longSpace.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> { }
                Resource.Status.ERROR -> Toast.makeText(context, "Error fetching space data.", Toast.LENGTH_SHORT).show()
                Resource.Status.SUCCESS -> {
                    it.data?.let { space -> memberItemAdapter.submitList(space.members) }
                }
            }
        }

        spaceInfoViewModel.leaveResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> { }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, "Error leaving space.", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.SUCCESS -> {
                    findNavController().popBackStack(R.id.navigation_my_spaces, false)
                }
            }
        }
    }

    private fun createConfirmationDialog(
        title: String,
        message: String,
        acceptCallback: DialogInterface.OnClickListener
    ) {
        val builder: AlertDialog.Builder = requireActivity().let {
            AlertDialog.Builder(it)
        }

        builder.apply {
            setTitle(title)
            setMessage(message)

            setPositiveButton(R.string.yes, acceptCallback)
            setNegativeButton(R.string.cancel) { dialog, _ -> dialog.cancel() }
            setCancelable(true)
        }

        builder.show()
    }

}