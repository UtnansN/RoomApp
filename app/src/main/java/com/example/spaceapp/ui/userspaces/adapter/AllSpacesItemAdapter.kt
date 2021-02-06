package com.example.spaceapp.ui.userspaces.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.BR
import com.example.spaceapp.R
import com.example.spaceapp.data.model.remote.UserSpacesDTO
import com.example.spaceapp.databinding.ItemMySpaceBinding

class AllSpacesItemAdapter(
    @NonNull diffCallback: DiffUtil.ItemCallback<UserSpacesDTO>,
    private val clickListener: (UserSpacesDTO) -> Unit
) : ListAdapter<UserSpacesDTO, AllSpacesItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(private val binding: ItemMySpaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(space: UserSpacesDTO) {
            binding.setVariable(BR.space, space)
            binding.executePendingBindings()
        }
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
        val inflater = LayoutInflater.from(parent.context)

        val binding: ItemMySpaceBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_my_space, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val space: UserSpacesDTO = getItem(position)

        holder.bind(space)
        holder.itemView.setOnClickListener {
            clickListener(space)
        }
    }

}