package raf.rma.catalist.breeds.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import raf.rma.catalist.breeds.serialization.BraindeadBoolSerializer
import raf.rma.catalist.breeds.serialization.CommaSeparatedListSerializer
import raf.rma.catalist.breeds.serialization.RangeIntSerializer

@Serializable
data class BreedsModel(
    val id: String,
    val name: String,
    @SerialName("alt_names")
    @Serializable(with = CommaSeparatedListSerializer::class)
    val altNames: List<String> = emptyList(),
    val description: String,
    @Serializable(with = CommaSeparatedListSerializer::class)
    val temperament: List<String>,
    val image: BreedsImage? = null,
    @SerialName("origin")
    val country: String,
    @SerialName("life_span")
    @Serializable(with = RangeIntSerializer::class)
    val lifeSpan: Range<Int>,
    val weight: Weight,
    val adaptability: Int,
    @SerialName("affection_level")
    val affectionLevel: Int,
    @SerialName("child_friendly")
    val childFriendly: Int,
    @SerialName("energy_level")
    val energyLevel: Int,
    val grooming: Int,
    @SerialName("rare")
    @Serializable(with = BraindeadBoolSerializer::class)
    val isRare: Boolean,
    @SerialName("wikipedia_url")
    val wikiUrl: String? = null
)

data class Range<T>(
    val from: T,
    val to: T
)

@Serializable
data class Weight(
    @Serializable(with = RangeIntSerializer::class)
    val imperial: Range<Int>,
    @Serializable(with = RangeIntSerializer::class)
    val metric: Range<Int>
)

@Serializable
data class BreedsImage(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String
)