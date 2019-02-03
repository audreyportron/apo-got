package com.apo.mobgengot.ui.houses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apo.mobgengot.R
import com.apo.mobgengot.domain.house.House
import kotlinx.android.synthetic.main.house_item.view.*

class HouseAdapter() :
    PagedListAdapter<House, RecyclerView.ViewHolder>(houseDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HouseViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HouseViewHolder).bindTo(getItem(position))
    }

    companion object {
        val houseDiffCallback = object : DiffUtil.ItemCallback<House>() {
            override fun areItemsTheSame(oldItem: House, newItem: House): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: House, newItem: House): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class HouseViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.house_item, parent, false)
) {
    fun bindTo(house: House?) {
        itemView.apply{
            house_item_name.text = house?.name
            house_item_region.text = house?.region
            house_item_title.text = house?.title
        }

    }
}