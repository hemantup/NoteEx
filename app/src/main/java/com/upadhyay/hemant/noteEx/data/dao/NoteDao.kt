package com.upadhyay.hemant.noteEx.data.dao

import androidx.room.*
import com.upadhyay.hemant.noteEx.data.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notesTable ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notesTable WHERE id = :id")
    suspend fun getSpecificNote(id: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: NoteEntity)

    @Delete
    suspend fun deleteNotes(note: NoteEntity)

    @Query("DELETE FROM notesTable WHERE id = :id")
    suspend fun deleteSpecificNote(id: Int)

    @Update
    suspend fun updateNotes(note: NoteEntity)
}

