package raf.rma.catalist.breeds.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import raf.rma.catalist.breeds.repository.BreedsRepository

data class BreedsListViewModel(
    val repository: BreedsRepository = BreedsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BreedsListState())
    val state = _state.asStateFlow()

    init {
        fetchBreeds()
    }

    fun fetchBreeds() {
        _state.getAndUpdate { it.copy(loading = true) }
        viewModelScope.launch {
            try {
                delay(2000)
                val breeds = withContext(Dispatchers.IO) {
                    repository.fetchAllBreeds()
                }

                _state.getAndUpdate {
                    it.copy(items = breeds)
                }
            } catch (error: Exception) {
                error.printStackTrace()
            } finally {
                _state.getAndUpdate { it.copy(loading = false) }
            }

        }
    }
}
