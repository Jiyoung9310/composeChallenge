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

class PuppyRepository private constructor(private val puppyDao: PuppyDao) {

    fun getPuppies() = puppyDao.getPuppies()

    fun getPuppy(puppyId: Int) = puppyDao.getPuppy(puppyId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PuppyRepository? = null

        fun getInstance(puppyDao: PuppyDao) =
            instance ?: synchronized(this) {
                instance ?: PuppyRepository(puppyDao).also { instance = it }
            }
    }
}
