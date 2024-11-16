package com.example.simpleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleapp.databinding.ItemAnimalBinding
import com.example.simpleapp.model.Animal

class AnimalAdapter : ListAdapter<Animal, AnimalAdapter.ViewHolder>(AnimalItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemAnimalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: Animal) {
            with(binding) {
                // Название животного
                animalName.text = animal.name
                // Происхождение
                animalOrigin.text = animal.taxonomy.kingdom
                // Длина
                animalLength.text = "Length: ${animal.height}"
                // Дружелюбие к семье
                animalFamilyFriendly.text = "Family Friendly: ${animal.groupBehavior}"
                // Линька
                animalShedding.text = "Shedding: ${animal.diet}"
                // Диета
                animalDiet.text = "Diet: ${animal.diet}"
                // Продолжительность жизни
                animalLifespan.text = "Lifespan: ${animal.lifespan}"
                // Слоган
                animalSlogan.text = "Slogan: ${animal.slogan}"
                // Цвет
                animalColor.text = "Color: ${animal.color}"
            }
        }
    }
}
