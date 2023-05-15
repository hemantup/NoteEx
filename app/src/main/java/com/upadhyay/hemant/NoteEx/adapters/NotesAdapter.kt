package com.upadhyay.hemant.NoteEx.adapters

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.upadhyay.hemant.NoteEx.adapters.NotesAdapter.NoteViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.upadhyay.hemant.NoteEx.R
import android.widget.TextView
import android.widget.LinearLayout
import android.graphics.PorterDuff
import android.util.Log
import android.view.View
import com.upadhyay.hemant.NoteEx.data.entities.NoteEntity

class NotesAdapter(private val noteList: List<NoteEntity>) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_container_note, parent, false)
        return NoteViewHolder(v)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.setNote(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textTitle: TextView
        var textDateTime: TextView
        var textDescriptionNoteText: TextView
        var linearLayout: LinearLayout

        init {
            textTitle = itemView.findViewById(R.id.textTitle)
            textDateTime = itemView.findViewById(R.id.textDateTime)
            textDescriptionNoteText = itemView.findViewById(R.id.textDescriptionNoteText)
            linearLayout = itemView.findViewById(R.id.linearLayoutNote)
        }

        fun setNote(note: NoteEntity) {
            if (note.title.trim { it <= ' ' }.isEmpty()) {
                textTitle.visibility = View.GONE
            } else {
                textTitle.text = note.title
            }
            if (note.noteText.trim { it <= ' ' }.isEmpty()) {
                textDescriptionNoteText.visibility = View.GONE
            } else {
                textDescriptionNoteText.text = note.noteText
            }
            textDateTime.text = note.dateTime
            Log.d("TAG", "Adapter.getNoteColor: " + note.color)
            if (note.color != null) {
                when (note.color) {
                    "colorDefaultNoteColor" -> linearLayout.background.setColorFilter(
                        Color.parseColor("#333333"), PorterDuff.Mode.SRC_ATOP
                    )
                    "colorNoteColor2" -> linearLayout.background.setColorFilter(
                        Color.parseColor("#FDBE3B"),
                        PorterDuff.Mode.SRC_ATOP
                    )
                    "colorNoteColor3" -> linearLayout.background.setColorFilter(
                        Color.parseColor("#FF4842"),
                        PorterDuff.Mode.SRC_ATOP
                    )
                    "colorNoteColor4" -> linearLayout.background.setColorFilter(
                        Color.parseColor("#3A52FC"),
                        PorterDuff.Mode.SRC_ATOP
                    )
                    "colorNoteColor5" -> linearLayout.background.setColorFilter(
                        Color.parseColor("#000000"),
                        PorterDuff.Mode.SRC_ATOP
                    )
                }
            }
        }
    }
}