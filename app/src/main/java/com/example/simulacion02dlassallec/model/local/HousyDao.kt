package com.example.simulacion02dlassallec.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HousyDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHouses(list: List<Housy>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneHouse(details: HousyDetails)

    @Query("SELECT * FROM housy")
    fun getAllHouses(): LiveData<List<Housy>>

    @Query("SELECT * FROM housydetails WHERE id=:id")
    fun getOneHouse(id: Int): LiveData<HousyDetails>



}