package com.example.simulacion02dlassallec.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HousyRetrofitClient {


    companion object{
        private const val BASE_URL= "http://my-json-server.typicode.com/Himuravidal/mansions/"

        fun retrofitInstance(): HousyApi {
            val retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(HousyApi::class.java)
        }

    }
}