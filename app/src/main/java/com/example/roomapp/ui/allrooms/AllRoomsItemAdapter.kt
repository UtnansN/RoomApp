package com.example.roomapp.ui.allrooms

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
import com.example.roomapp.data.models.Room

class AllRoomsItemAdapter(@NonNull diffCallback: DiffUtil.ItemCallback<Room>) : ListAdapter<Room, AllRoomsItemAdapter.ViewHolder>(diffCallback) {

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
                .inflate(R.layout.my_rooms_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room: Room = getItem(position)

        holder.txtName.text = room.name
        holder.imgRoomAvatar.setImageResource(R.drawable.ic_home_black_24dp)
    }

}