package com.example.androiddevchallenge.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puppies")
data class Puppy (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val puppyId: Int = 0,
    val name: String,
    val species: String,
    val sex: String,
    val age: String,
    val imageUrl: String = ""
) {
    override fun toString() = name
}
