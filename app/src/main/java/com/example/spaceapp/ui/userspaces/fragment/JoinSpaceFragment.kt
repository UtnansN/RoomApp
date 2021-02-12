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
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.ui.userspaces.viewmodel.JoinSpaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinSpaceFragment : Fragment() {

    private lateinit var joinSpaceViewModel: JoinSpaceViewModel
    private lateinit var roomCodeInput: EditText
    private lateinit var btnSubmit: Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        joinSpaceViewModel = ViewModelProvider(this).get(JoinSpaceViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_space_join, container, false)

        roomCodeInput = root.findViewById(R.id.txt_join_space_code)
        btnSubmit = root.findViewById(R.id.btn_join_space)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        joinSpaceViewModel.joinSpaceResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {}
                Resource.Status.SUCCESS -> {
                    val bundle = bundleOf("spaceCode" to it.data?.code, "title" to it.data?.name)
                    findNavController().navigate(R.id.action_navigation_space_join_create_to_specific_space, bundle)
                }
                Resource.Status.ERROR -> {}
            }
        }

        btnSubmit.setOnClickListener {
            joinSpaceViewModel.joinSpace(roomCodeInput.text.toString())
        }
    }

}