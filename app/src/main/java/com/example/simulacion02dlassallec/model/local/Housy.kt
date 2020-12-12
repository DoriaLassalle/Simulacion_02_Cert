package com.example.simulacion02dlassallec.model.local
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Housy(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("price")
    val price: Int
)