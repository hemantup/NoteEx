package com.upadhyay.hemant.NoteEx.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.upadhyay.hemant.NoteEx.data.dao.NoteDao
import com.upadhyay.hemant.NoteEx.data.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao?

}