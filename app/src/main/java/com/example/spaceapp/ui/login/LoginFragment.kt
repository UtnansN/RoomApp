package com.example.spaceapp.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.R
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.databinding.FragmentLoginBinding
import com.example.spaceapp.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loading: ProgressBar
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root = binding.root
        binding.viewModel = loginViewModel

        loading = root.findViewById(R.id.loading)
        
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.loginResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.ERROR -> {
                    loading.visibility = View.GONE
                    showLoginFailed(it.message.orEmpty())
                }
                Resource.Status.SUCCESS -> {
                    loading.visibility = View.GONE
                    loginViewModel.invokeUserSave()
                    requireActivity().setResult(Activity.RESULT_OK)
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    requireActivity().finish()
                    startActivity(intent)
                }
                Resource.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
    }

}