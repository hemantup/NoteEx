package com.upadhyay.hemant.NoteEx.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.upadhyay.hemant.NoteEx.R
import android.annotation.SuppressLint
import android.os.AsyncTask
import com.upadhyay.hemant.NoteEx.data.database.NotesDatabase
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.upadhyay.hemant.NoteEx.data.entities.NoteEntity
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteActivity() : AppCompatActivity() {
    private var imageViewBack: ImageView? = null
    private var imageViewSaveNote: ImageView? = null
    private var inputNoteTitle: EditText? = null
    private var inputNoteText: EditText? = null
    private var textDateTime: TextView? = null
    private var selectedNoteColor: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        initViews()
        imageViewBack!!.setOnClickListener({ view: View? -> onBackPressed() })
        textDateTime!!.text = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
            .format(Date())
        imageViewSaveNote!!.setOnClickListener({ view: View? -> saveNote() })
        selectedNoteColor = "colorDefaultNoteColor"


        // Bottom Sheet Behavior is set
        initMiscellaneous()
    }

    private fun saveNote() {
        if (inputNoteTitle!!.text.toString().trim { it <= ' ' }
                .isEmpty() && inputNoteText!!.text.toString().trim { it <= ' ' }
                .isEmpty()) {
            Toast.makeText(this, "Note can't be empty!", Toast.LENGTH_SHORT).show()
            return
        }
        val noteEntity = NoteEntity()
        if (!inputNoteTitle!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            noteEntity.title = inputNoteTitle!!.text.toString()
        }
        if (!inputNoteText!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            noteEntity.noteText = inputNoteText!!.text.toString()
        }
        noteEntity.dateTime = textDateTime!!.text.toString()
        Log.d("TAG", "selectedNoteColor: $selectedNoteColor")
        noteEntity.color = selectedNoteColor


        // Room doesn't allow database operation on the Main Thread
        // So, the action will be performed using AsyncTask
        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask() : AsyncTask<Void?, Void?, Void?>() {
            protected override fun doInBackground(vararg voids: Void): Void? {
                NotesDatabase.getNotesDatabase(applicationContext)?.noteDao()?.insertNote(noteEntity)
                return null
            }

            override fun onPostExecute(unused: Void?) {
                super.onPostExecute(unused)
                val intent = Intent()
                setResult(RESULT_OK, intent)
                finish()
            }
        }
        SaveNoteTask().execute()
    }

    private fun initViews() {
        imageViewBack = findViewById(R.id.imageViewBack)
        inputNoteTitle = findViewById(R.id.inputNoteTitle)
        inputNoteText = findViewById(R.id.inputNoteText)
        textDateTime = findViewById(R.id.textDateTime)
        imageViewSaveNote = findViewById(R.id.imageViewSaveNote)
    }

    private fun initMiscellaneous() {
        val layoutMiscellaneous = findViewById<LinearLayout>(R.id.linearLayoutMiscellaneous)
        val bottomSheetBehavior: BottomSheetBehavior<*> =
            BottomSheetBehavior.from(layoutMiscellaneous)
        layoutMiscellaneous.findViewById<View>(R.id.textMiscellaneous).setOnClickListener(
            View.OnClickListener {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                }
            })
        val imageColor1 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor1)
        val imageColor2 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor2)
        val imageColor3 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor3)
        val imageColor4 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor4)
        val imageColor5 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor5)
        layoutMiscellaneous.findViewById<View>(R.id.viewColor1)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    selectedNoteColor = "colorDefaultNoteColor"
                    imageColor1.setImageResource(R.drawable.ic_done)
                    imageColor2.setImageResource(0)
                    imageColor3.setImageResource(0)
                    imageColor4.setImageResource(0)
                    imageColor5.setImageResource(0)
                    setNoteBackgroundColor()
                }
            })
        layoutMiscellaneous.findViewById<View>(R.id.viewColor2)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    selectedNoteColor = "colorNoteColor2"
                    imageColor1.setImageResource(0)
                    imageColor2.setImageResource(R.drawable.ic_done)
                    imageColor3.setImageResource(0)
                    imageColor4.setImageResource(0)
                    imageColor5.setImageResource(0)
                    setNoteBackgroundColor()
                }
            })
        layoutMiscellaneous.findViewById<View>(R.id.viewColor3)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    selectedNoteColor = "colorNoteColor3"
                    imageColor1.setImageResource(0)
                    imageColor2.setImageResource(0)
                    imageColor3.setImageResource(R.drawable.ic_done)
                    imageColor4.setImageResource(0)
                    imageColor5.setImageResource(0)
                    setNoteBackgroundColor()
                }
            })
        layoutMiscellaneous.findViewById<View>(R.id.viewColor4)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    selectedNoteColor = "colorNoteColor4"
                    imageColor1.setImageResource(0)
                    imageColor2.setImageResource(0)
                    imageColor3.setImageResource(0)
                    imageColor4.setImageResource(R.drawable.ic_done)
                    imageColor5.setImageResource(0)
                    setNoteBackgroundColor()
                }
            })
        layoutMiscellaneous.findViewById<View>(R.id.viewColor5)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    selectedNoteColor = "colorNoteColor5"
                    imageColor1.setImageResource(0)
                    imageColor2.setImageResource(0)
                    imageColor3.setImageResource(0)
                    imageColor4.setImageResource(0)
                    imageColor5.setImageResource(R.drawable.ic_done)
                    setNoteBackgroundColor()
                }
            })
    }

    private fun setNoteBackgroundColor() {
        val rootCoordinatorLayout = findViewById<CoordinatorLayout>(R.id.rootCoordinatorLayout)
        when (selectedNoteColor) {
            "colorDefaultNoteColor" -> rootCoordinatorLayout.setBackgroundResource(R.color.colorDefaultNoteColor)
            "colorNoteColor2" -> rootCoordinatorLayout.setBackgroundResource(R.color.colorNoteColor2)
            "colorNoteColor3" -> rootCoordinatorLayout.setBackgroundResource(R.color.colorNoteColor3)
            "colorNoteColor4" -> rootCoordinatorLayout.setBackgroundResource(R.color.colorNoteColor4)
            "colorNoteColor5" -> rootCoordinatorLayout.setBackgroundResource(R.color.colorNoteColor5)
        }
    }
}