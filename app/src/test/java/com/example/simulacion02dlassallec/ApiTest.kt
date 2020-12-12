package com.example.simulacion02dlassallec

import com.example.simulacion02dlassallec.model.local.Housy
import com.example.simulacion02dlassallec.model.local.HousyDetails
import com.example.simulacion02dlassallec.model.network.HousyApi
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mHousyApiTest: HousyApi

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val mRetrofit = Retrofit.Builder()
            .baseUrl(mMockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mHousyApiTest = mRetrofit.create(HousyApi::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getOneHouse()= runBlocking {

        val productDetail = HousyDetails("whatever", true, "casa en la playa", 1,
            "beachhouse", "urlx", 250000000, false, 200)
        val houseId = 1
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(productDetail)))

        val result = mHousyApiTest.fetchOneHouse(houseId)

        val body = result.body()
        assertThat(body?.cause).isNotEmpty()
        assertThat(body?.credit).isEqualTo(true)
        assertThat(body?.name).contains("beach")
    }

    @Test
    fun getAllHouses() = runBlocking {

        val houseTest = Housy(1, "dpto", "url", 250000000)
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(houseTest)))

        val result = mHousyApiTest.fetchAllHouses()

        assertThat(result).isNotNull()
        val body = result.body()
        if (body != null) {
            assertThat(body.size).isEqualTo(1)

        }

        val request = mMockWebServer.takeRequest()
        println(request.path)
        Truth.assertThat(request.path).isEqualTo("mansions/")
    }


}