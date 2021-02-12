package com.example.spaceapp.ui.exactspace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceapp.data.model.UserBriefDTO
import com.example.spaceapp.databinding.ItemMemberBinding
import com.example.spaceapp.BR

class UserItemAdapter(
    @NonNull diffCallback: DiffUtil.ItemCallback<UserBriefDTO>
) : ListAdapter<UserBriefDTO, UserItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(private val binding: ItemMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserBriefDTO) {
            binding.setVariable(BR.user, user)
            binding.executePendingBindings()
        }
    }

    class UserDiff : DiffUtil.ItemCallback<UserBriefDTO>() {
        override fun areItemsTheSame(oldItem: UserBriefDTO, newItem: UserBriefDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserBriefDTO, newItem: UserBriefDTO): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemMemberBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: UserBriefDTO = getItem(position)
        holder.bind(user)
    }


}