package com.upadhyay.hemant.NoteEx.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.upadhyay.hemant.NoteEx.data.dao.NoteDao
import kotlin.jvm.Synchronized
import androidx.room.Room
import com.upadhyay.hemant.NoteEx.data.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao?

    companion object {
        private var notesDatabase: NotesDatabase? = null
        @Synchronized
        fun getNotesDatabase(context: Context?): NotesDatabase? {
            if (notesDatabase == null) {
                notesDatabase = Room.databaseBuilder(
                    context!!,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()
            }
            return notesDatabase
        }
    }
}