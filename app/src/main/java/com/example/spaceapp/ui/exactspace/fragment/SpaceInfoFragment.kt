package com.example.spaceapp.ui.exactspace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.R
import com.example.spaceapp.ui.exactspace.viewmodel.SpaceInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpaceInfoFragment : Fragment() {

    private lateinit var spaceInfoViewModel: SpaceInfoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        spaceInfoViewModel = ViewModelProvider(this).get(SpaceInfoViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_space_about, container, false)

        return root
    }

}