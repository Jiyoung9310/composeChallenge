package com.example.androiddevchallenge.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PuppyDao {
    @Query("SELECT * FROM puppies ORDER BY name")
    fun getPuppies(): Flow<List<Puppy>>

    @Query("SELECT * FROM puppies WHERE id = :puppyId")
    fun getPuppy(puppyId: Int): Flow<Puppy>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(puppies: List<Puppy>)
}
