package raf.rma.catalist.breeds.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import raf.rma.catalist.breeds.serialization.CommaSeparatedListSerializer

@Serializable
data class BreedsModel(
    val id: String,
    val name: String,
    @SerializedName("alt_names")
    @Serializable(with = CommaSeparatedListSerializer::class)
    val altNames: List<String> = emptyList(),
    @SerializedName("description")
    val description: String,
    @SerializedName("temperament")
    @Serializable(with = CommaSeparatedListSerializer::class)
    val temperament: List<String>,
//    @SerializedName("reference_image_id")
//    val imageId: String = "",
    val image: BreedsImage? = null

)

@Serializable
data class BreedsImage(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String
)