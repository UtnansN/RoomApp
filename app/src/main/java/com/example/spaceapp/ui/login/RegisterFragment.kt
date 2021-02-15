package com.example.spaceapp.ui.login

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spaceapp.R
import com.example.spaceapp.databinding.FragmentRegisterBinding
import com.yalantis.ucrop.UCrop
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var btnSelectAvatar: Button
    private lateinit var imgAvatar: ImageView

    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) {uri: Uri? ->
        if (uri != null) {
            val tempFile = File.createTempFile("crop_", ".png", requireContext().cacheDir)

            val options = UCrop.Options()
            options.apply {
                setCircleDimmedLayer(true)
                setCompressionFormat(Bitmap.CompressFormat.PNG)
            }

            val cropIntent = UCrop.of(uri, tempFile.toUri())
                .withAspectRatio(1f, 1f)
                .withOptions(options)
                .getIntent(requireContext())

            cropImage.launch(cropIntent)
        }
    }

    private val cropImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val resultUri = UCrop.getOutput(it.data!!)
            registerViewModel.imageURI.set(resultUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        val binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = registerViewModel

        val root = binding.root

        btnSelectAvatar = root.findViewById(R.id.btn_register_select_avatar)
        imgAvatar = root.findViewById(R.id.img_register_avatar)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSelectAvatar.setOnClickListener {
            selectImage.launch("image/*")
        }
    }
    
}