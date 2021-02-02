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
import com.example.spaceapp.data.model.remote.UserSpacesDTO

class AllSpacesItemAdapter(
    @NonNull diffCallback: DiffUtil.ItemCallback<UserSpacesDTO>,
    private val clickListener: (UserSpacesDTO) -> Unit
) : ListAdapter<UserSpacesDTO, AllSpacesItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = itemView.findViewById(R.id.txt_space_name)
        val imgSpaceAvatar: ImageView = itemView.findViewById(R.id.img_space)
    }

    class SpaceDiff : DiffUtil.ItemCallback<UserSpacesDTO>() {
        override fun areItemsTheSame(oldItem: UserSpacesDTO, newItem: UserSpacesDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserSpacesDTO, newItem: UserSpacesDTO): Boolean {
            return oldItem.code == newItem.code
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_space, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val space: UserSpacesDTO = getItem(position)

        holder.txtName.text = space.name
        holder.imgSpaceAvatar.setImageResource(R.drawable.ic_launcher_background)

        holder.itemView.setOnClickListener {
            clickListener(space)
        }
    }

}