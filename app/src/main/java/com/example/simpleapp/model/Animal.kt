package com.example.simpleapp.model

import java.util.UUID

data class Taxonomy(
    val kingdom: String,
    val phylum: String,
    val `class`: String,
    val order: String,
    val family: String,
    val genus: String,
    val scientificName: String
)

data class Animal(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val taxonomy: Taxonomy,
    val locations: List<String>,
    val prey: String,
    val nameOfYoung: String,
    val groupBehavior: String,
    val estimatedPopulationSize: String,
    val biggestThreat: String,
    val mostDistinctiveFeature: String,
    val gestationPeriod: String,
    val habitat: String,
    val diet: String,
    val averageLitterSize: String,
    val lifestyle: String,
    val commonName: String,
    val numberOfSpecies: String,
    val location: String,
    val slogan: String,
    val group: String,
    val color: String,
    val skinType: String,
    val topSpeed: String,
    val lifespan: String,
    val weight: String,
    val height: String,
    val ageOfSexualMaturity: String,
    val ageOfWeaning: String
)

val animalMapper: (AnimalResponse) -> Animal = { response ->
    Animal(
        name = response.name ?: "Unknown",
        taxonomy = response.taxonomy?.let {
            Taxonomy(
                kingdom = it.kingdom ?: "Unknown",
                phylum = it.phylum ?: "Unknown",
                `class` = it.`class` ?: "Unknown",
                order = it.order ?: "Unknown",
                family = it.family ?: "Unknown",
                genus = it.genus ?: "Unknown",
                scientificName = it.scientificName ?: "Unknown"
            )
        } ?: Taxonomy("Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown"),
        locations = response.locations ?: emptyList(),
        prey = response.characteristics?.prey ?: "Unknown",
        nameOfYoung = response.characteristics?.nameOfYoung ?: "Unknown",
        groupBehavior = response.characteristics?.groupBehavior ?: "Unknown",
        estimatedPopulationSize = response.characteristics?.estimatedPopulationSize ?: "Unknown",
        biggestThreat = response.characteristics?.biggestThreat ?: "Unknown",
        mostDistinctiveFeature = response.characteristics?.mostDistinctiveFeature ?: "Unknown",
        gestationPeriod = response.characteristics?.gestationPeriod ?: "Unknown",
        habitat = response.characteristics?.habitat ?: "Unknown",
        diet = response.characteristics?.diet ?: "Unknown",
        averageLitterSize = response.characteristics?.averageLitterSize ?: "Unknown",
        lifestyle = response.characteristics?.lifestyle ?: "Unknown",
        commonName = response.characteristics?.commonName ?: "Unknown",
        numberOfSpecies = response.characteristics?.numberOfSpecies ?: "Unknown",
        location = response.characteristics?.location ?: "Unknown",
        slogan = response.characteristics?.slogan ?: "Unknown",
        group = response.characteristics?.group ?: "Unknown",
        color = response.characteristics?.color ?: "Unknown",
        skinType = response.characteristics?.skinType ?: "Unknown",
        topSpeed = response.characteristics?.topSpeed ?: "Unknown",
        lifespan = response.characteristics?.lifespan ?: "Unknown",
        weight = response.characteristics?.weight ?: "Unknown",
        height = response.characteristics?.height ?: "Unknown",
        ageOfSexualMaturity = response.characteristics?.ageOfSexualMaturity ?: "Unknown",
        ageOfWeaning = response.characteristics?.ageOfWeaning ?: "Unknown"
    )
}