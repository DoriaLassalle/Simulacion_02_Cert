package com.example.simulacion02dlassallec.model.local
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class HousyDetails(
    @SerializedName("cause")
    val cause: String,
    @SerializedName("credit")
    val credit: Boolean,
    @SerializedName("description")
    val description: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("renovation")
    val renovation: Boolean,
    @SerializedName("size")
    val size: Int
)