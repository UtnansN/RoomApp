package com.example.spaceapp.ui.exactspace.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.Utils
import com.example.spaceapp.data.model.local.Event
import com.example.spaceapp.data.model.remote.EventDTO

class EventItemAdapter(@NonNull diffCallback: DiffUtil.ItemCallback<EventDTO>
) : ListAdapter<EventDTO, EventItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = itemView.findViewById(R.id.txt_event_name)
        val txtTime: TextView = itemView.findViewById(R.id.txt_event_time)
        val txtLocation: TextView = itemView.findViewById(R.id.txt_event_location)
        val frameLocation: View = itemView.findViewById(R.id.frame_event_location)
        val txtDescription: TextView = itemView.findViewById(R.id.txt_event_description)
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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event: EventDTO = getItem(position)

        holder.txtName.text = event.name
        holder.txtTime.text = event.dateTime

        Utils.setTextOrViewGoneIfBlank(holder.txtLocation, event.location)
        Utils.setViewGoneIfBlank(holder.frameLocation, event.location)

        Utils.setTextOrViewGoneIfBlank(holder.txtDescription, event.description)

        holder.itemView.setOnClickListener {
            // clickListener(room)
        }
    }

}