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
import com.example.spaceapp.data.model.EventBriefDTO
import com.example.spaceapp.databinding.ItemEventBinding
import com.example.spaceapp.generated.callback.OnClickListener

class EventItemAdapter(
    @NonNull diffCallback: DiffUtil.ItemCallback<EventBriefDTO>,
    private val dateTimeConverter: DateTimeConverter,
    private val listener: (EventBriefDTO) -> Unit
) : ListAdapter<EventBriefDTO, EventItemAdapter.ViewHolder>(diffCallback) {


    class ViewHolder(private val binding: ItemEventBinding, private val dateTimeConverter: DateTimeConverter) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eventBrief: EventBriefDTO) {
            binding.setVariable(BR.event, eventBrief)
            binding.setVariable(BR.dateTimeConverter, dateTimeConverter)
            binding.executePendingBindings()
        }
    }

    class EventDiff : DiffUtil.ItemCallback<EventBriefDTO>() {
        override fun areItemsTheSame(oldItem: EventBriefDTO, newItem: EventBriefDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: EventBriefDTO, newItem: EventBriefDTO): Boolean {
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
        val eventBrief: EventBriefDTO = getItem(position)
        holder.bind(eventBrief)

        holder.itemView.setOnClickListener {
            listener(eventBrief)
        }
    }

}