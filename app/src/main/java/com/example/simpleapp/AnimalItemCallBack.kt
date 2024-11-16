package com.example.simpleapp

import androidx.recyclerview.widget.DiffUtil
import com.example.simpleapp.model.Animal

class AnimalItemCallBack : DiffUtil.ItemCallback<Animal>() {
    override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem == newItem
    }
}

