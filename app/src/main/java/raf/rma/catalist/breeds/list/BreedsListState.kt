package raf.rma.catalist.breeds.list

import raf.rma.catalist.breeds.model.BreedsModel

data class BreedsListState (
    val items: List<BreedsModel> = emptyList(),
)