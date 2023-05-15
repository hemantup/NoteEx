package com.upadhyay.hemant.NoteEx.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.upadhyay.hemant.NoteEx.adapters.NotesAdapter
import android.os.Bundle
import android.content.Intent
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.os.AsyncTask
import com.upadhyay.hemant.NoteEx.data.database.NotesDatabase
import android.util.Log
import android.widget.ImageView
import com.akshatbhuhagal.mynotes.R
import com.upadhyay.hemant.NoteEx.data.entities.NoteEntity
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var notesRecyclerView: RecyclerView? = null
    private var noteList: MutableList<NoteEntity>? = null
    private var notesAdapter: NotesAdapter? = null
    private var imageViewAddNoteFab: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        imageViewAddNoteFab!!.setOnClickListener {
            startActivityForResult(
                Intent(applicationContext, CreateNoteActivity::class.java),
                REQUEST_CODE_ADD_NOTE
            )
        }
        notesRecyclerView!!.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        noteList = ArrayList()
        notesAdapter = NotesAdapter(noteList)
        notesRecyclerView!!.adapter = notesAdapter
        notes
    }

    // Async task to get all the saved notes
    val notes: Unit
        get() {
            // Async task to get all the saved notes
            class GetNotesTask : AsyncTask<Void?, Void?, List<NoteEntity>>() {
                protected override fun doInBackground(vararg voids: Void): List<NoteEntity> {
                    return NotesDatabase
                        .getNotesDatabase(applicationContext)
                        .noteDao().allNotes
                }

                override fun onPostExecute(notes: List<NoteEntity>) {
                    super.onPostExecute(notes)
                    Log.d("TAG", "note: $notes")
                    Log.d("TAG", "noteList: " + noteList.toString())
                    if (noteList!!.size == 0) {
                        noteList!!.addAll(notes)
                        notesAdapter!!.notifyDataSetChanged()
                    } else {
                        noteList!!.add(0, notes[0])
                        notesAdapter!!.notifyItemInserted(0)
                    }
                    notesRecyclerView!!.smoothScrollToPosition(0)
                }
            }
            GetNotesTask().execute()
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            notes
        }
    }

    private fun initViews() {
        imageViewAddNoteFab = findViewById(R.id.imageViewAddNoteFab)
        notesRecyclerView = findViewById(R.id.notesRecyclerView)
    }

    companion object {
        const val REQUEST_CODE_ADD_NOTE = 1
    }
}