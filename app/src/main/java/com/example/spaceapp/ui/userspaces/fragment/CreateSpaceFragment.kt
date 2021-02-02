package com.example.spaceapp.ui.userspaces.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spaceapp.R
import com.example.spaceapp.data.model.local.Space
import com.example.spaceapp.ui.userspaces.viewmodel.CreateSpaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateSpaceFragment : Fragment() {

    private lateinit var createSpaceViewModel: CreateSpaceViewModel

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

        val submitBtn = root.findViewById<Button>(R.id.btn_create_space)
        submitBtn.setOnClickListener {
            val room = Space(
                    id = 0,
                    name = spaceNameEditBox.text.toString(),
                    description = spaceDescriptionEditBox.text.toString(),
                    spaceCode = "ASDF"
            )
            createSpaceViewModel.addRoom(room)
            findNavController().navigate(R.id.action_navigation_space_join_create_to_my_spaces)
        }

        return root
    }

}