package com.example.spaceapp.ui.exactspace.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.spaceapp.R
import com.example.spaceapp.databinding.FragmentSpaceBinding
import com.example.spaceapp.ui.exactspace.adapter.SpaceViewPagerAdapter
import com.example.spaceapp.ui.exactspace.viewmodel.SpaceViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpaceFragment : Fragment() {

    private lateinit var spaceViewModel: SpaceViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var spaceCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.menu_info).isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_info -> findNavController()
                .navigate(SpaceFragmentDirections.actionNavigationSpaceToSpaceInfo())
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        spaceCode = requireArguments().getString("spaceCode")!!

        spaceViewModel = ViewModelProvider(this).get(SpaceViewModel::class.java)
        spaceViewModel.setSpaceId(spaceCode)

        val binding: FragmentSpaceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_space, container, false)
        val root = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = spaceViewModel

        tabLayout = root.findViewById(R.id.tabpager_tabs)
        viewPager = root.findViewById(R.id.tabpager_pager)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter =
            SpaceViewPagerAdapter(this, spaceCode)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.events)
                1 -> tab.text = resources.getString(R.string.discussions)
            }
        }.attach()
    }

}