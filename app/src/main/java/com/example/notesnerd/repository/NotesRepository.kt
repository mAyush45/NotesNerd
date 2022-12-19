package com.example.notesnerd.repository

import androidx.lifecycle.LiveData
import com.example.notesnerd.dao.NotesDao
import com.example.notesnerd.model.Notes


class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>> {
        return dao.getLowNotes()
    }

    fun getMedNotes(): LiveData<List<Notes>> {
        return dao.getMedNotes()
    }

    fun getHighNotes(): LiveData<List<Notes>> {
        return dao.getHighNotes()
    }

    fun insertNodes(notes: Notes) {
        return dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        return dao.updateNotes(notes)
    }

}