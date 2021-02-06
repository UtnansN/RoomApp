package com.example.spaceapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.spaceapp.R
import com.example.spaceapp.ui.exactspace.adapter.SpaceViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : FragmentActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val viewPager = findViewById<ViewPager2>(R.id.viewpager_auth)
        viewPager.adapter = AuthStateAdapter(this)
    }
}