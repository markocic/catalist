package raf.rma.catalist.breeds.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import raf.rma.catalist.breeds.repository.BreedsRepository

class BreedsDetailsViewModel constructor (
    val breedId: String,
    val repository: BreedsRepository = BreedsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(BreedsDetailsState(breedId = breedId))
    val state = _state.asStateFlow()

    init {
        fetchBreed(breedId)
    }

    private fun fetchBreed(breed: String) {
        _state.getAndUpdate { it.copy(loading = true) }
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.fetchBreed(breed)
                }

                _state.getAndUpdate {
                    it.copy(breed = result)
                }
            } catch (error: Exception) {
                error.printStackTrace()
                _state.getAndUpdate {
                    it.copy(error = error)
                }
            } finally {
                _state.getAndUpdate { it.copy(loading = false) }
            }

        }
    }
}