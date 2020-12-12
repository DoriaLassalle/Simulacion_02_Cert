package com.example.simulacion02dlassallec.model.network

import com.example.simulacion02dlassallec.model.local.Housy
import com.example.simulacion02dlassallec.model.local.HousyDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HousyApi {

    @GET("mansions/")
    suspend fun fetchAllHouses(): Response<List<Housy>>

    @GET("details/{id}")
    suspend fun fetchOneHouse(@Path("id") id: Int): Response<HousyDetails>
}