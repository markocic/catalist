package raf.rma.catalist.breeds.api

import raf.rma.catalist.breeds.model.BreedsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsApi {
    @GET("/v1/breeds")
    suspend fun fetchAllBreeds(): List<BreedsModel>

    @GET("/v1/breeds/{id}")
    suspend fun fetchBreed(@Path("id") id: String): BreedsModel
}