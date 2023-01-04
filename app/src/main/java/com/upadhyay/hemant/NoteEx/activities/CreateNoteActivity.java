package com.upadhyay.hemant.NoteEx.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
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

    private String selectedNoteColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        initViews();

        imageViewBack.setOnClickListener(view -> onBackPressed());

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        imageViewSaveNote.setOnClickListener(view -> saveNote());

        selectedNoteColor = "colorDefaultNoteColor";



        // Bottom Sheet Behavior is set
        initMiscellaneous();
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
        Log.d("TAG", "selectedNoteColor: " + selectedNoteColor);
        note.setColor(selectedNoteColor);


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

    private void initMiscellaneous(){
        final LinearLayout layoutMiscellaneous = findViewById(R.id.linearLayoutMiscellaneous);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(layoutMiscellaneous);
        layoutMiscellaneous.findViewById(R.id.textMiscellaneous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        ImageView imageColor1 = layoutMiscellaneous.findViewById(R.id.imageColor1);
        ImageView imageColor2 = layoutMiscellaneous.findViewById(R.id.imageColor2);
        ImageView imageColor3 = layoutMiscellaneous.findViewById(R.id.imageColor3);
        ImageView imageColor4 = layoutMiscellaneous.findViewById(R.id.imageColor4);
        ImageView imageColor5 = layoutMiscellaneous.findViewById(R.id.imageColor5);

        layoutMiscellaneous.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNoteColor = "colorDefaultNoteColor";
                imageColor1.setImageResource(R.drawable.ic_done);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setNoteBackgroundColor();
            }
        });
        layoutMiscellaneous.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNoteColor = "colorNoteColor2";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(R.drawable.ic_done);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setNoteBackgroundColor();
            }
        });
        layoutMiscellaneous.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNoteColor = "colorNoteColor3";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(R.drawable.ic_done);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setNoteBackgroundColor();
            }
        });
        layoutMiscellaneous.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNoteColor = "colorNoteColor4";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(R.drawable.ic_done);
                imageColor5.setImageResource(0);
                setNoteBackgroundColor();
            }
        });
        layoutMiscellaneous.findViewById(R.id.viewColor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNoteColor = "colorNoteColor5";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(R.drawable.ic_done);
                setNoteBackgroundColor();
            }
        });



    }

    private void setNoteBackgroundColor() {
        CoordinatorLayout rootCoordinatorLayout = findViewById(R.id.rootCoordinatorLayout);

        switch (selectedNoteColor){
            case "colorDefaultNoteColor":
                rootCoordinatorLayout.setBackgroundResource(R.color.colorDefaultNoteColor);
                break;
            case "colorNoteColor2":
                rootCoordinatorLayout.setBackgroundResource(R.color.colorNoteColor2);
                break;
            case "colorNoteColor3":
                rootCoordinatorLayout.setBackgroundResource(R.color.colorNoteColor3);
                break;
            case "colorNoteColor4":
                rootCoordinatorLayout.setBackgroundResource(R.color.colorNoteColor4);
                break;
            case "colorNoteColor5":
                rootCoordinatorLayout.setBackgroundResource(R.color.colorNoteColor5);
                break;
        }

    }
}