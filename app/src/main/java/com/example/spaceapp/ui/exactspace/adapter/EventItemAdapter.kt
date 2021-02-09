package com.example.spaceapp.ui.exactspace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.BR
import com.example.spaceapp.utils.DateTimeConverter
import com.example.spaceapp.R
import com.example.spaceapp.data.model.dto.EventDTO
import com.example.spaceapp.databinding.ItemEventBinding

class EventItemAdapter(
    @NonNull diffCallback: DiffUtil.ItemCallback<EventDTO>,
    private val dateTimeConverter: DateTimeConverter
) : ListAdapter<EventDTO, EventItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(private val binding: ItemEventBinding, private val dateTimeConverter: DateTimeConverter) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventDTO) {
            binding.setVariable(BR.event, event)
            binding.setVariable(BR.dateTimeConverter, dateTimeConverter)
            binding.executePendingBindings()
        }
    }

    class EventDiff : DiffUtil.ItemCallback<EventDTO>() {
        override fun areItemsTheSame(oldItem: EventDTO, newItem: EventDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: EventDTO, newItem: EventDTO): Boolean {
            return oldItem.eventId == newItem.eventId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ItemEventBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_event, parent, false)

        return ViewHolder(binding, dateTimeConverter)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event: EventDTO = getItem(position)
        holder.bind(event)

        holder.itemView.setOnClickListener {
            // clickListener(room)
        }
    }

}