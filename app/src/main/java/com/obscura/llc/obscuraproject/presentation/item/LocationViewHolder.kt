package com.obscura.llc.obscuraproject.presentation.item

import androidx.recyclerview.widget.RecyclerView
import com.obscura.llc.obscuraproject.databinding.ItemLocationBinding
import com.obscura.llc.obscuraproject.presentation.activities.location.LocationModel


class LocationViewHolder(val binding: ItemLocationBinding): RecyclerView.ViewHolder(binding.root) {

    private var location: LocationModel? = null

    fun bind(location: LocationModel) {
        this.location = location
        binding.location = this.location
    }
}