package com.example.simulacion02dlassallec.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simulacion02dlassallec.HousyRepository
import com.example.simulacion02dlassallec.model.local.Housy
import com.example.simulacion02dlassallec.model.local.HousyDataBase
import com.example.simulacion02dlassallec.model.local.HousyDetails

class HousyViewModel(application: Application):AndroidViewModel(application) {

    private val myRepository:HousyRepository

    val allHouses:LiveData<List<Housy>>
    val houseSelection=MutableLiveData<Int>()

    init {
        val myDao=HousyDataBase.getDatabase(application).housyDao()
        myRepository=HousyRepository(myDao)
        allHouses=myRepository.housesList
        myRepository.getHousesFromApi()
    }

    fun getOneHouse(id:Int):LiveData<HousyDetails>{
        myRepository.getOneHouseFromApi(id)
        return myRepository.getOneHouse(id)
    }

    fun houseSelected(houseId:Int){
        houseSelection.value=houseId
    }


}