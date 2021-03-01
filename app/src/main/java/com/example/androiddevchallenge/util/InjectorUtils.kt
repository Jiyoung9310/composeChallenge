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
package com.example.androiddevchallenge.util

import android.content.Context
import com.example.androiddevchallenge.data.AppDatabase
import com.example.androiddevchallenge.data.PuppyRepository
import com.example.androiddevchallenge.viewmodel.PuppyDetailViewModelFactory
import com.example.androiddevchallenge.viewmodel.PuppyListViewModelFactory

object InjectorUtils {
    private fun getPuppyRepository(context: Context): PuppyRepository {
        return PuppyRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).puppyDao()
        )
    }

    fun providePuppyListViewModelFactory(context: Context): PuppyListViewModelFactory {
        return PuppyListViewModelFactory(getPuppyRepository(context))
    }

    fun providePuppyDetailViewModelFactory(context: Context, id: Int): PuppyDetailViewModelFactory {
        return PuppyDetailViewModelFactory(getPuppyRepository(context), id)
    }
}
