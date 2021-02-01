package com.example.roomapp.ui.allrooms.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.data.model.local.Room

class AllRoomsItemAdapter(@NonNull diffCallback: DiffUtil.ItemCallback<Room>,
                          private val clickListener: (Room) -> Unit
) : ListAdapter<Room, AllRoomsItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = itemView.findViewById(R.id.txt_room_name)
        val imgRoomAvatar: ImageView = itemView.findViewById(R.id.img_room)
    }

    class RoomDiff : DiffUtil.ItemCallback<Room>() {
        override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_my_room, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room: Room = getItem(position)

        holder.txtName.text = room.name
        holder.imgRoomAvatar.setImageResource(R.drawable.ic_launcher_background)

        holder.itemView.setOnClickListener {
            clickListener(room)
        }
    }

}