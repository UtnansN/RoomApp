package com.example.spaceapp.ui.exactspace.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spaceapp.R
import com.example.spaceapp.databinding.FragmentEventCreateBinding
import com.example.spaceapp.ui.exactspace.viewmodel.CreateEventViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.util.*
import android.text.format.DateFormat as ContextualDateFormat

@AndroidEntryPoint
class CreateEventFragment : Fragment() {

    private lateinit var viewModel: CreateEventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(CreateEventViewModel::class.java)

        val binding: FragmentEventCreateBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_event_create, container, false)

        val root = binding.root
        val spaceCode = requireArguments().getString("spaceCode").orEmpty()

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.spaceCode = spaceCode

        val txtTime: EditText = root.findViewById(R.id.txt_event_create_time)
        val txtDate: EditText = root.findViewById(R.id.txt_event_create_date)

        txtTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar[Calendar.HOUR_OF_DAY]
            val minute = calendar[Calendar.MINUTE]

            val timePicker = TimePickerDialog(requireContext(), {
                    _, selectedHour, selectedMinute ->
                val format = ContextualDateFormat.getTimeFormat(requireContext())

                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                calendar.set(Calendar.MINUTE, selectedMinute)
                txtTime.setText( format.format(calendar.time) )},
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
                // Date displayed is always in English. Time format depends on system settings.
                val format = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH)
                calendar.set(selectedYear, selectedMonth, selectedDay)
                txtDate.setText(format.format(calendar.time))}, year, month, day)

            datePicker.datePicker.minDate = calendar.timeInMillis
            datePicker.show()
        }

        val btnSubmit: Button = root.findViewById(R.id.btn_create_event)
        btnSubmit.setOnClickListener {
            viewModel.submitData(spaceCode)
            findNavController().popBackStack()
        }

        return root
    }

}