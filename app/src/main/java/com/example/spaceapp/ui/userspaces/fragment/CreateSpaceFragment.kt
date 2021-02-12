package com.example.spaceapp.ui.userspaces.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spaceapp.R
import com.example.spaceapp.data.model.CreateSpaceDTO
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.ui.userspaces.viewmodel.CreateSpaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateSpaceFragment : Fragment() {

    private lateinit var createSpaceViewModel: CreateSpaceViewModel

    private lateinit var submitBtn: Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        createSpaceViewModel =
                ViewModelProvider(this).get(CreateSpaceViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_space_create, container, false)

        val spaceNameEditBox = root.findViewById<EditText>(R.id.txt_create_space_name)
        val spaceDescriptionEditBox = root.findViewById<EditText>(R.id.txt_create_space_description)

        submitBtn = root.findViewById(R.id.btn_join_space)

        submitBtn.setOnClickListener {
            val room = CreateSpaceDTO(
                name = spaceNameEditBox.text.toString(),
                description = spaceDescriptionEditBox.text.toString()
            )
            createSpaceViewModel.addRoom(room)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createSpaceViewModel.createSpaceResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    submitBtn.isEnabled = false
                }
                Resource.Status.SUCCESS -> {
                    val bundle = bundleOf("spaceCode" to it.data?.code, "title" to it.data?.name)
                    findNavController().navigate(R.id.action_navigation_space_join_create_to_specific_space, bundle)
                }
                Resource.Status.ERROR -> {
                    submitBtn.isEnabled = true
                }
            }
        })
    }

}