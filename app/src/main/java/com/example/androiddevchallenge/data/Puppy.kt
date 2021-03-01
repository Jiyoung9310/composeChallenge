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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puppies")
data class Puppy(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val puppyId: Int = 0,
    val name: String,
    val species: String,
    val sex: String,
    val age: String,
    val color: String,
    val description: String,
    val imageUrl: String = ""
) {
    override fun toString() = name
}
