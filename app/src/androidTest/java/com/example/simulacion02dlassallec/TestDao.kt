package com.example.simulacion02dlassallec

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.simulacion02dlassallec.model.local.Housy
import com.example.simulacion02dlassallec.model.local.HousyDao
import com.example.simulacion02dlassallec.model.local.HousyDataBase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mHouseDao: HousyDao
    lateinit var db: HousyDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, HousyDataBase::class.java).build()
        mHouseDao = db.housyDao()
    }
    @After
    fun shutDown() {
        db.close()
    }

    @Test
    fun insertHouseList() = runBlocking {

        val housesList = listOf<Housy>(Housy(1, "dpto", "urlx", 150000000))
        mHouseDao.insertAllHouses(housesList)
        mHouseDao.getAllHouses().observeForever {
            assertThat(it).isNotEmpty()

        }

    }
}