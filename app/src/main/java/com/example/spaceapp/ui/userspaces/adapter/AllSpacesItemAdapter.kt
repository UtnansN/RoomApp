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
import com.example.spaceapp.data.model.ShortSpaceDTO
import com.example.spaceapp.databinding.ItemMySpaceBinding
import com.example.spaceapp.utils.DateTimeConverter

class AllSpacesItemAdapter(
    @NonNull diffCallback: DiffUtil.ItemCallback<ShortSpaceDTO>,
    private val dateTimeConverter: DateTimeConverter,
    private val clickListener: (ShortSpaceDTO) -> Unit
) : ListAdapter<ShortSpaceDTO, AllSpacesItemAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(private val binding: ItemMySpaceBinding, private val dateTimeConverter: DateTimeConverter) : RecyclerView.ViewHolder(binding.root) {
        fun bind(space: ShortSpaceDTO) {
            binding.setVariable(BR.space, space)
            binding.setVariable(BR.dateTimeConverter, dateTimeConverter)
            binding.executePendingBindings()
        }
    }

    class SpaceDiff : DiffUtil.ItemCallback<ShortSpaceDTO>() {
        override fun areItemsTheSame(oldItem: ShortSpaceDTO, newItem: ShortSpaceDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ShortSpaceDTO, newItem: ShortSpaceDTO): Boolean {
            return oldItem.code == newItem.code
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ItemMySpaceBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_my_space, parent, false)

        return ViewHolder(binding, dateTimeConverter)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val space: ShortSpaceDTO = getItem(position)

        holder.bind(space)
        holder.itemView.setOnClickListener {
            clickListener(space)
        }
    }

}