package raf.rma.catalist.breeds.list

import raf.rma.catalist.breeds.domain.BreedInfo

data class BreedsListState (
    val items: List<BreedInfo> = emptyList(),
)