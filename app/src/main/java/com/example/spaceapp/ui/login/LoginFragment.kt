package com.example.spaceapp.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.R
import com.example.spaceapp.data.model.dto.Resource
import com.example.spaceapp.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val username = root.findViewById<EditText>(R.id.username)
        val password = root.findViewById<EditText>(R.id.password)
        val login = root.findViewById<Button>(R.id.login)
        val loading = root.findViewById<ProgressBar>(R.id.loading)

        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.ERROR -> {
                    loading.visibility = View.GONE
                    showLoginFailed(it.message.orEmpty())
                }
                Resource.Status.SUCCESS -> {
                    loading.visibility = View.GONE
                    loginViewModel.invokeUserSave(username.text.toString(), password.text.toString())
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

        login.setOnClickListener {
            loginViewModel.login(username.text.toString(), password.text.toString())
        }

        return root
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
    }

}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}