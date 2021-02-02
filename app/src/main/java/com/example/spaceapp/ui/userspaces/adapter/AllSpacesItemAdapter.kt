package com.example.spaceapp.ui.userspaces.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.R
import com.example.spaceapp.data.model.local.Space

class AllSpacesItemAdapter(@NonNull diffCallback: DiffUtil.ItemCallback<Space>,
                           private val clickListener: (Space) -> Unit
) : ListAdapter<Space, AllSpacesItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = itemView.findViewById(R.id.txt_space_name)
        val imgSpaceAvatar: ImageView = itemView.findViewById(R.id.img_space)
    }

    class SpaceDiff : DiffUtil.ItemCallback<Space>() {
        override fun areItemsTheSame(oldItem: Space, newItem: Space): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Space, newItem: Space): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_my_space, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val space: Space = getItem(position)

        holder.txtName.text = space.name
        holder.imgSpaceAvatar.setImageResource(R.drawable.ic_launcher_background)

        holder.itemView.setOnClickListener {
            clickListener(space)
        }
    }

}