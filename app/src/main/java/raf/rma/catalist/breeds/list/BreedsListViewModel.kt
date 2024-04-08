package raf.rma.catalist.breeds.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import raf.rma.catalist.breeds.domain.BreedInfo
import raf.rma.catalist.breeds.repository.BreedsRepository

data class BreedsListViewModel(
    val repository: BreedsRepository = BreedsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BreedsListState())
    val state = _state.asStateFlow()

    init {
        val item1 = BreedInfo(
            id = "abys",
            name = "Abyssinian",
            altNames = "",
            description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            temperament = listOf("Active", "Energetic", "Independent"),
            imageURL = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg"
        )

        _state.getAndUpdate {
            it.copy(items = listOf(item1, item1))
        }

    }
}
