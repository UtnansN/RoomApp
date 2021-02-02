package com.example.spaceapp.ui.exactspace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spaceapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscussionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_itemlist, container, false)

        return root
    }

}