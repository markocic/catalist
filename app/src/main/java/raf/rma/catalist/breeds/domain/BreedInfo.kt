package raf.rma.catalist.breeds.domain

data class BreedInfo(
    val name: String,
    val altNames: String,
    val description: String,
    val temperament: List<String>,
    val imageURL: String,
)
