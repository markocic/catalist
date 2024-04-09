package raf.rma.catalist.breeds.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
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

    private val events = MutableSharedFlow<SearchUiEvent>()
    fun setEvent(event: SearchUiEvent) = viewModelScope.launch { events.emit(event) }

    init {
        observeEvents()
        fetchInitialBreeds()
    }

    private fun observeEvents() {
        viewModelScope.launch {
            events.collect {
                when (it) {
                    is SearchUiEvent.SearchSubmitted -> {
                        filter(name = it.term)
                    }

                    else -> {}
                }
            }
        }
    }
    private fun fetchInitialBreeds() {
        _state.getAndUpdate { it.copy(loading = true) }
        viewModelScope.launch {
            try {
                val breeds = withContext(Dispatchers.IO) {
                    repository.fetchAllBreeds()
                }

                _state.getAndUpdate {
                    it.copy(
                        allBreeds = breeds,
                        results = breeds
                    )
                }
            } catch (error: Exception) {
                error.printStackTrace()
            } finally {
                _state.getAndUpdate { it.copy(loading = false) }
            }
        }
    }

    private fun filter(name: String) {
        _state.getAndUpdate {
            it.copy(
                results = it.allBreeds.filter { breed ->
                    breed.name.contains(name, ignoreCase = true)
                }
            )
        }
    }
}