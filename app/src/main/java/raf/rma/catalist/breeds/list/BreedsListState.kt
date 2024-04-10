package raf.rma.catalist.breeds.list

import raf.rma.catalist.breeds.model.BreedsModel

data class BreedsListState (
    val loading: Boolean = false,
    val items: List<BreedsModel> = emptyList(),
    val error: Throwable? = null
)