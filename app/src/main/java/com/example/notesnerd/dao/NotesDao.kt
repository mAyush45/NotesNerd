package com.example.notesnerd.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesnerd.model.Notes


@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes" )
    fun getNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes where priority =1" )
    fun getHighNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes where priority =2" )
    fun getMedNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes where priority =3" )
    fun getLowNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //same aaye toh replace krdo
    fun insertNotes(notes:Notes)

    @Query("DELETE FROM Notes where id=:uid")
    fun deleteNotes(uid:Int)

    @Update()
    fun updateNotes(notes:Notes)
}