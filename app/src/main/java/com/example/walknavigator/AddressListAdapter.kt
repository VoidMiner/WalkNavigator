package com.example.walknavigator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.walknavigator.databinding.ItemAddressBinding

class AddressListAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<String, AddressListAdapter.AddressViewHolder>(AddressDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(address: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(inflater, parent, false)
        return AddressViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = getItem(position)
        holder.bind(address)
    }

    inner class AddressViewHolder(
        private val binding: ItemAddressBinding,
        private val itemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val address = getItem(position)
                    itemClickListener.onItemClick(address)
                }
            }
        }

        fun bind(address: String) {
            binding.addressText.text = address
        }
    }

    private class AddressDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    // Метод для обновления списка адресов
    fun updateAddresses(newAddresses: List<String>) {
        submitList(newAddresses)
    }
}




