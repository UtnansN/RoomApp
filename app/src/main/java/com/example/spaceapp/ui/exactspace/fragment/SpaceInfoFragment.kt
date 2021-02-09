package com.example.spaceapp.ui.exactspace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.R
import com.example.spaceapp.data.model.dto.Resource
import com.example.spaceapp.databinding.FragmentSpaceAboutBinding
import com.example.spaceapp.ui.exactspace.viewmodel.SpaceInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpaceInfoFragment : Fragment() {

    private lateinit var spaceInfoViewModel: SpaceInfoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        spaceInfoViewModel = ViewModelProvider(this).get(SpaceInfoViewModel::class.java)

        val binding: FragmentSpaceAboutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_space_about, container, false)
        val spaceCode = requireArguments().getString("spaceCode", null)!!
        spaceInfoViewModel.getSpaceInfo(spaceCode)

        binding.viewModel = spaceInfoViewModel
        binding.lifecycleOwner = this

        spaceInfoViewModel.spaceInfo.observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.LOADING -> {}
                Resource.Status.ERROR -> Toast.makeText(context, "Error fetching space data.", Toast.LENGTH_SHORT).show()
                Resource.Status.SUCCESS -> {}
            }
        }

        return binding.root
    }

}