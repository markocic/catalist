package raf.rma.catalist.breeds.api

import raf.rma.catalist.breeds.model.BreedsModel
import retrofit2.http.GET

interface BreedsApi {
    @GET("/v1/breeds")
    suspend fun fetchAllBreeds(): List<BreedsModel>
}