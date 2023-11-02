package com.maelin.taskapp.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var integerList = mutableStateListOf<Int>()

    fun increaseItems() {
        println("increase items")
        integerList.add(itemsCount() + 1)
    }

    fun itemsCount(): Int {
        println("get items count")
        return integerList.size
    }

    fun getItems(): List<Int> {
        return integerList.toList()
    }

}