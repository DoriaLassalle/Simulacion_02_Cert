package com.example.simulacion02dlassallec

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.simulacion02dlassallec.model.local.HousyDao
import com.example.simulacion02dlassallec.model.local.HousyDetails
import com.example.simulacion02dlassallec.model.network.HousyRetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HousyRepository(private val myHousyDao: HousyDao) {

    private val myRetrofit = HousyRetrofitClient.retrofitInstance()
    val housesList = myHousyDao.getAllHouses()

    fun getOneHouse(id: Int): LiveData<HousyDetails> {
        return myHousyDao.getOneHouse(id)
    }


    fun getHousesFromApi() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { myRetrofit.fetchAllHouses() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {

                    myHousyDao.insertAllHouses(it)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }
        service.onFailure {
            Log.e("ERROR", it.message.toString())

        }
    }

    fun getOneHouseFromApi(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { myRetrofit.fetchOneHouse(id) }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {

                    myHousyDao.insertOneHouse(it)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())

        }

    }

}