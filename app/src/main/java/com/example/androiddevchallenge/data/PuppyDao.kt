package com.example.androiddevchallenge.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PuppyDao {
    @Query("SELECT * FROM puppies ORDER BY name")
    fun getPuppies(): LiveData<List<Puppy>>

    @Query("SELECT * FROM puppies WHERE id = :puppyId")
    fun getPuppy(puppyId: String): LiveData<Puppy>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(puppies: List<Puppy>)
}
