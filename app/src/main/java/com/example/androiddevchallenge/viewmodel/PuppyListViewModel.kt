package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.PuppyRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PuppyListViewModel internal constructor(
    private val repo: PuppyRepository
) : ViewModel() {
    private val _puppies = MutableLiveData<List<Puppy>>()
    val puppies: LiveData<List<Puppy>> = _puppies

    fun getPuppies() {
        viewModelScope.launch {
            repo.getPlants().collect {
                _puppies.value = it
            }
        }
    }
}