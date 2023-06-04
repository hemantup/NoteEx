package com.upadhyay.hemant.noteEx.activities.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upadhyay.hemant.noteEx.data.repo.NotesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val notesRepo: NotesRepo) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    val notes = searchQuery.flatMapLatest { query->
        notesRepo.notes!!.map { it.filter { it.title?.contains(query, ignoreCase = true) == true } }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun onSearchQueryChanged(query:String) = viewModelScope.launch {
        searchQuery.emit(query)
    }

}