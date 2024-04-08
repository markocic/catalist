package raf.rma.catalist.breeds.details

import raf.rma.catalist.breeds.model.BreedsModel

data class BreedsDetailsState(
    val loading: Boolean = false,
    val breed: BreedsModel? = null,
    val breedId: String,
)
