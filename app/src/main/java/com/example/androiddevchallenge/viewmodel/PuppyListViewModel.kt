package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.PuppyRepository

class PuppyListViewModel internal constructor(
    plantRepository: PuppyRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
}