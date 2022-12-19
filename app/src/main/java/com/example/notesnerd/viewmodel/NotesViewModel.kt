package com.example.notenerd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesnerd.database.NotesDatabase
import com.example.notesnerd.model.Notes
import com.example.notesnerd.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun insertNotes(notes: Notes) {
        return repository.insertNodes(notes)
    }

    fun deleteNotes(id: Int) {
        return repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        return repository.updateNotes(notes)
    }

    fun getAllNotes(): LiveData<List<Notes>> {
        return repository.getAllNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>> {
        return repository.getLowNotes()
    }

    fun getMedNotes(): LiveData<List<Notes>> {
        return repository.getMedNotes()
    }

    fun getHighNotes(): LiveData<List<Notes>> {
        return repository.getHighNotes()
    }

}