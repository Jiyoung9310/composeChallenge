/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
