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

class EventItemAdapter(@NonNull diffCallback: DiffUtil.ItemCallback<Event>
) : ListAdapter<Event, EventItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = itemView.findViewById(R.id.txt_event_name)
        val txtTime: TextView = itemView.findViewById(R.id.txt_event_time)
        val txtLocation: TextView = itemView.findViewById(R.id.txt_event_location)
        val frameLocation: View = itemView.findViewById(R.id.frame_event_location)
        val txtDescription: TextView = itemView.findViewById(R.id.txt_event_description)
    }

    class EventDiff : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
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
        val event: Event = getItem(position)

        holder.txtName.text = event.name
        holder.txtTime.text = event.time

        Utils.setTextOrViewGoneIfBlank(holder.txtLocation, event.location)
        Utils.setViewGoneIfBlank(holder.frameLocation, event.location)

        Utils.setTextOrViewGoneIfBlank(holder.txtDescription, event.description)

        holder.itemView.setOnClickListener {
            // clickListener(room)
        }
    }

}