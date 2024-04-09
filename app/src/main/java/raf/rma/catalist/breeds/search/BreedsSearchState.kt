package raf.rma.catalist.breeds.search

import raf.rma.catalist.breeds.model.BreedsModel

data class BreedsSearchState (
    val loading: Boolean = false,
    val searchTerm: String = "",
    val results: List<BreedsModel> = emptyList()
)