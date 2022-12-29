package com.upadhyay.hemant.NoteEx.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upadhyay.hemant.NoteEx.R;
import com.upadhyay.hemant.NoteEx.entities.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{

     private List<Note> noteList;

     public NotesAdapter(List<Note> noteList) {
          this.noteList = noteList;
     }

     @NonNull
     @Override
     public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_note,parent,false);
          return new NoteViewHolder(v);
     }

     @Override
     public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
               holder.setNote(noteList.get(position));
     }

     @Override
     public int getItemCount() {
          return noteList.size();
     }

     @Override
     public int getItemViewType(int position) {
          return position;
     }

     public class NoteViewHolder extends RecyclerView.ViewHolder{

          TextView textTitle,textDateTime,textDescriptionNoteText;

          public NoteViewHolder(@NonNull View itemView) {
               super(itemView);
               textTitle = itemView.findViewById(R.id.textTitle);
               textDateTime = itemView.findViewById(R.id.textDateTime);
               textDescriptionNoteText = itemView.findViewById(R.id.textDescriptionNoteText);

          }


          void setNote(Note note){
               if(note.getTitle().trim().isEmpty()){
                    textTitle.setVisibility(View.GONE);
               }else {
                    textTitle.setText(note.getTitle());
               }

               if(note.getNoteText().trim().isEmpty()){
                    textDescriptionNoteText.setVisibility(View.GONE);
               }else {
                    textDescriptionNoteText.setText(note.getNoteText());
               }

               textDateTime.setText(note.getDateTime());

          }
     }
}
