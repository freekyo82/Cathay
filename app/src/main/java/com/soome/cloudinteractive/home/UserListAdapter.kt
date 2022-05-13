package com.soome.cloudinteractive.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soome.cloudinteractive.databinding.UserListItemBinding
import com.soome.cloudinteractive.entity.UserData

class UserListAdapter(
    var onUserClick: (UserData) -> Unit,
) : PagingDataAdapter<UserData, UserListAdapter.ListUserViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(
            UserListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ListUserViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserData) = with(binding) {
            ivAvatar.load(item.avatarUrl)
            tvLogin.text = item.login
            tvStaff.isVisible = item.isAdmin
            root.setOnClickListener {
                onUserClick.invoke(item)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UserData>() {
            override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean =
                oldItem == newItem
        }
    }
}