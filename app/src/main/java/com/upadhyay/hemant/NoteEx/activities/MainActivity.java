package com.upadhyay.hemant.NoteEx.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.upadhyay.hemant.NoteEx.R;
import com.upadhyay.hemant.NoteEx.activities.CreateNoteActivity;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1;

    private ImageView imageViewAddNoteFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        imageViewAddNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(getApplicationContext(), CreateNoteActivity.class),
                        REQUEST_CODE_ADD_NOTE
                );
            }
        });



    }

    private void initViews() {
        imageViewAddNoteFab = findViewById(R.id.imageViewAddNoteFab);
    }
}