package com.upadhyay.hemant.NoteEx.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.upadhyay.hemant.NoteEx.R;
import com.upadhyay.hemant.NoteEx.database.NotesDatabase;
import com.upadhyay.hemant.NoteEx.entities.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {

    private ImageView imageViewBack, imageViewSaveNote;
    private EditText inputNoteTitle, inputNoteText;
    private TextView textDateTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        initViews();

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        imageViewSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

    }


    private void saveNote(){
        if(inputNoteTitle.getText().toString().trim().isEmpty() && inputNoteText.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Note can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        final Note note = new Note();
        if(!inputNoteTitle.getText().toString().trim().isEmpty()){
            note.setTitle(inputNoteTitle.getText().toString());
        }
        if(!inputNoteText.getText().toString().trim().isEmpty()){
            note.setNoteText(inputNoteText.getText().toString());
        }
        note.setDateTime(textDateTime.getText().toString());


        // Room doesn't allow database operation on the Main Thread
        // So, the action will be performed using AsyncTask

        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getNotesDatabase(getApplicationContext()).noteDao().insertNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }

        new SaveNoteTask().execute();


    }

    private void initViews() {
        imageViewBack =  findViewById(R.id.imageViewBack);
        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteText = findViewById(R.id.inputNoteText);
        textDateTime = findViewById(R.id.textDateTime);
        imageViewSaveNote = findViewById(R.id.imageViewSaveNote);
    }
}