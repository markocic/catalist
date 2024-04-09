package raf.rma.catalist.breeds.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import raf.rma.catalist.breeds.repository.BreedsRepository

class BreedsSearchViewModel(
    val repository: BreedsRepository = BreedsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BreedsSearchState())
    val state = _state.asStateFlow()

    init {
        fetchInitialBreeds()
    }

    private fun fetchInitialBreeds() {
        _state.getAndUpdate { it.copy(loading = true) }
        viewModelScope.launch {
            try {
                val breeds = withContext(Dispatchers.IO) {
                    repository.fetchAllBreeds()
                }

                _state.getAndUpdate {
                    it.copy(results = breeds)
                }
            } catch (error: Exception) {
                error.printStackTrace()
            } finally {
                _state.getAndUpdate { it.copy(loading = false) }
            }
        }
    }

    fun filter(name: String) {
        _state.getAndUpdate {
            it.copy(
                results = it.results.filter { breed ->
                    breed.name.contains(name, ignoreCase = true)
                }
            )
        }
    }
}