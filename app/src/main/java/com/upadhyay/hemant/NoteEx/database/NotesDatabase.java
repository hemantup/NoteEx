package com.upadhyay.hemant.NoteEx.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.upadhyay.hemant.NoteEx.dao.NoteDao;
import com.upadhyay.hemant.NoteEx.entities.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase notesDatabase;

    public static synchronized NotesDatabase getNotesDatabase(Context context){
        if(notesDatabase == null){
            notesDatabase = Room.databaseBuilder(
                    context,
                    NotesDatabase.class,
                    "notes_database"
            ).build();
        }
        return notesDatabase;
    }

    public abstract NoteDao noteDao();

}
