package com.example.androiddevchallenge.util

import android.content.Context
import com.example.androiddevchallenge.data.AppDatabase
import com.example.androiddevchallenge.data.PuppyRepository
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
}