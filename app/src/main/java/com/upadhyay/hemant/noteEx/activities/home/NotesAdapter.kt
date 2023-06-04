package com.upadhyay.hemant.noteEx.activities.home

import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.upadhyay.hemant.noteEx.data.entities.NoteEntity
import com.upadhyay.hemant.noteEx.databinding.ItemContainerNoteBinding

class NotesAdapter : ListAdapter<NoteEntity, NotesAdapter.NoteViewHolder>(diffCallback) {

    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemContainerNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setOnClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }

    interface OnItemClickListener {
        fun onClicked(notesId: Int)
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemContainerNoteBinding.bind(itemView)

        fun bind(note: NoteEntity) {
            with(binding) {
                tvTitle.text = note.title
                tvDesc.text = note.noteText
                tvDateTime.text = note.dateTime

                note.color?.let {
                    cardView.setCardBackgroundColor(Color.parseColor(note.color))
                }

                if (note.imgPath.isNullOrEmpty().not()) {
                    imgNote.setImageBitmap(BitmapFactory.decodeFile(note.imgPath))
                    imgNote.visibility = View.VISIBLE
                } else {
                    imgNote.visibility = View.GONE
                }

                if (note.webLink.isNullOrEmpty().not()) {
                    tvWebLink.text = note.webLink
                    tvWebLink.visibility = View.VISIBLE
                } else {
                    tvWebLink.visibility = View.GONE
                }

                cardView.setOnClickListener {
                    listener.onClicked(note.id)
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<NoteEntity>() {
            override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}
