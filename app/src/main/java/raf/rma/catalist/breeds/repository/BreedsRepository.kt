package raf.rma.catalist.breeds.repository

import raf.rma.catalist.breeds.api.BreedsApi
import raf.rma.catalist.networking.retrofit

object BreedsRepository {

    private val breedsApi: BreedsApi = retrofit.create(BreedsApi::class.java)

    suspend fun fetchAllBreeds() = breedsApi.fetchAllBreeds()
    suspend fun fetchBreed(breed: String) = breedsApi.fetchBreed(breed)

}