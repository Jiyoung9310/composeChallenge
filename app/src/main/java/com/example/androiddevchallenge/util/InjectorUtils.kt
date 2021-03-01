package com.example.androiddevchallenge.util

import android.content.Context
import com.example.androiddevchallenge.data.AppDatabase
import com.example.androiddevchallenge.data.PuppyRepository

object InjectorUtils {
    private fun getPuppyRepository(context: Context): PuppyRepository {
        return PuppyRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).puppyDao()
        )
    }
}