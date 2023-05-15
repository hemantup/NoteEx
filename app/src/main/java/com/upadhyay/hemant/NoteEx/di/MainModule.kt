package com.upadhyay.hemant.NoteEx.di

import android.content.Context
import androidx.room.Room
import com.upadhyay.hemant.NoteEx.data.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context):NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java, "notes.db").build()

    @Provides
    fun providesNotesDao(dataBase: NotesDatabase) = dataBase.noteDao()
}