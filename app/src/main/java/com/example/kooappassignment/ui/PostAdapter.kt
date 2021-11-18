package com.example.kooappassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kooappassignment.data.models.Data
import com.example.kooappassignment.databinding.RowBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Data = differ.currentList[position]
        holder.bind(post)
    }

    override fun getItemCount() = differ.currentList.size

    class ViewHolder(private val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Data) {
            binding.apply {
                tvTitle.text = post.title
                tvBody.text = post.body
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(
            oldItem: Data, newItem: Data
        ): Boolean {
            return oldItem.body == newItem.body
        }

        override fun areContentsTheSame(
            oldItem: Data, newItem: Data
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
}