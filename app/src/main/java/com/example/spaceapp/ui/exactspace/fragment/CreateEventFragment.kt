package com.example.spaceapp.ui.exactspace.fragment

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.transition.PathMotion
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import com.example.spaceapp.utils.DateTimeConverter
import com.example.spaceapp.R
import com.example.spaceapp.data.model.Resource
import com.example.spaceapp.databinding.FragmentEventCreateBinding
import com.example.spaceapp.ui.exactspace.viewmodel.CreateEventViewModel
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.yalantis.ucrop.UCrop
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.*
import javax.inject.Inject
import android.text.format.DateFormat as ContextualDateFormat

@AndroidEntryPoint
class CreateEventFragment : Fragment() {

    @Inject
    lateinit var dateTimeConverter: DateTimeConverter

    private lateinit var viewModel: CreateEventViewModel
    private lateinit var spaceCode: String
    private lateinit var txtTime: EditText
    private lateinit var txtDate: EditText
    private lateinit var imgEvent: ImageView
    private lateinit var btnRemoveImage: Button
    private lateinit var btnSubmit: Button

    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            val tempFile = File.createTempFile("crop_", ".png", requireContext().cacheDir)

            val options = UCrop.Options()
            options.apply {
                setCompressionFormat(Bitmap.CompressFormat.PNG)
            }

            val cropIntent = UCrop.of(uri, tempFile.toUri())
                .withAspectRatio(16f, 9f)
                .withOptions(options)
                .getIntent(requireContext())

            cropImage.launch(cropIntent)
        }
    }

    private val cropImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val resultUri = UCrop.getOutput(it.data!!)
            viewModel.imageURI.set(resultUri)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(CreateEventViewModel::class.java)
        spaceCode = requireArguments().getString("spaceCode").orEmpty()

        viewModel.spaceCode = spaceCode

        val binding = FragmentEventCreateBinding.inflate(inflater, container, false)

        val root = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        txtTime = root.findViewById(R.id.txt_event_create_time)
        txtDate = root.findViewById(R.id.txt_event_create_date)
        imgEvent = root.findViewById(R.id.img_event_create)
        btnRemoveImage = root.findViewById(R.id.btn_create_event_remove_image)
        btnSubmit = root.findViewById(R.id.btn_create_event)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar[Calendar.HOUR_OF_DAY]
            val minute = calendar[Calendar.MINUTE]

            val timePicker = TimePickerDialog(requireContext(), {
                    _, selectedHour, selectedMinute ->
                viewModel.chosenDate[Calendar.HOUR_OF_DAY] = selectedHour
                viewModel.chosenDate[Calendar.MINUTE] = selectedMinute

                txtTime.setText( dateTimeConverter.createTime(selectedHour, selectedMinute) )},
                hour, minute, ContextualDateFormat.is24HourFormat(activity))
            timePicker.show()
        }

        txtDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar[Calendar.DAY_OF_MONTH]
            val month = calendar[Calendar.MONTH]
            val year = calendar[Calendar.YEAR]

            val datePicker = DatePickerDialog(requireContext(), {
                    _, selectedYear, selectedMonth, selectedDay ->
                viewModel.chosenDate.set(selectedYear, selectedMonth, selectedDay)
                // Date displayed is always in English. Time format depends on system settings.
                txtDate.setText(dateTimeConverter.createDate(selectedYear, selectedMonth, selectedDay))}, year, month, day)
            datePicker.datePicker.minDate = calendar.timeInMillis
            datePicker.show()
        }

        imgEvent.setOnClickListener {
            selectImage.launch("image/*")
        }

        btnRemoveImage.setOnClickListener {
            viewModel.imageURI.set(null)
        }

        viewModel.eventBriefResource.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    val bundle = bundleOf("spaceCode" to spaceCode, "eventBrief" to it.data!!)
                    findNavController().navigate(R.id.action_navigation_create_event_to_navigation_event_expanded, bundle)
                }
            }
        }
    }

}