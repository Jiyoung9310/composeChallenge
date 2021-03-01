package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.PuppyRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PuppyDetailViewModel(private val repo: PuppyRepository,
                           private val puppyId: Int) : ViewModel() {

    private val _puppy = MutableLiveData<Puppy>()
    val puppy : LiveData<Puppy> = _puppy

    init {
        viewModelScope.launch {
            repo.getPuppy(puppyId)
                .collect {
                    _puppy.value = it
                }
        }
    }
}