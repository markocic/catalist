package raf.rma.catalist.breeds.search

import raf.rma.catalist.breeds.model.BreedsModel

data class BreedsSearchState (
    val loading: Boolean = false,
    val allBreeds: List<BreedsModel> = emptyList(),
    val results: List<BreedsModel> = emptyList(),
    val error: Throwable? = null
)

sealed class SearchUiEvent {
    data class SearchSubmitted(
        val term: String
    ) : SearchUiEvent()
}
