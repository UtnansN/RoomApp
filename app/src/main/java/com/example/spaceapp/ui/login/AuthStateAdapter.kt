package com.example.spaceapp.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AuthStateAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> LoginFragment()
        1 -> RegisterFragment()
        else -> throw IllegalStateException()
    }
}