package com.example.spaceapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.spaceapp.R
import com.example.spaceapp.ui.exactspace.adapter.SpaceViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : FragmentActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val viewPager = findViewById<ViewPager2>(R.id.viewpager_auth)
        viewPager.adapter = AuthStateAdapter(this)

        val tabs: TabLayout = findViewById(R.id.login_tabs)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.action_sign_in)
                1 -> getString(R.string.action_register)
                else -> "Tab"
            }
        }.attach()
    }


}