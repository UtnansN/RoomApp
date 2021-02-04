package com.example.spaceapp.ui.userspaces.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.R
import com.example.spaceapp.ui.userspaces.viewmodel.JoinSpaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinSpaceFragment : Fragment() {

    private lateinit var joinSpaceViewModel: JoinSpaceViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        joinSpaceViewModel =
                ViewModelProvider(this).get(JoinSpaceViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_space_join, container, false)

        requireActivity().title = "Join a space"
        val roomCodeInput: EditText = root.findViewById(R.id.txt_join_space_code)
        val btnSubmit: Button = root.findViewById(R.id.btn_join_space)

        btnSubmit.setOnClickListener {
            joinSpaceViewModel.joinSpace(roomCodeInput.text.toString())
        }

        return root
    }

}