package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.data.PuppyRepository

class PuppyDetailViewModelFactory(
    private val repository: PuppyRepository,
    private val puppyId: Int
    ) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PuppyDetailViewModel(repository, puppyId) as T
    }
}