package com.reemsd.day.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reemsd.day.databinding.ListItemBinding
import com.reemsd.day.network.TopPlaces


class PlacesAdapter : ListAdapter<TopPlaces, PlacesAdapter.PetViewHolder>(DiffCallback) {
    class PetViewHolder( var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(views: TopPlaces) {
            binding.result = views

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }
    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<TopPlaces>() {
        override fun areItemsTheSame(oldItem: TopPlaces, newItem: TopPlaces): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TopPlaces, newItem: TopPlaces): Boolean {
            return oldItem.image == newItem.image
        }
    }
    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        return PetViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }
    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val petPhoto = getItem(position)
        petPhoto.id
        holder.bind(petPhoto)

    }
}