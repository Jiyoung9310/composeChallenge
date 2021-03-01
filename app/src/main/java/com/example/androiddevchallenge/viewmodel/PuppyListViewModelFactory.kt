package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.data.PuppyRepository

class PuppyListViewModelFactory(
    private val repository: PuppyRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PuppyListViewModel(repository) as T
    }
}
