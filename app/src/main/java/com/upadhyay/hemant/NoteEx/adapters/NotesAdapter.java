package com.upadhyay.hemant.NoteEx.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
          LinearLayout linearLayout;

          public NoteViewHolder(@NonNull View itemView) {
               super(itemView);
               textTitle = itemView.findViewById(R.id.textTitle);
               textDateTime = itemView.findViewById(R.id.textDateTime);
               textDescriptionNoteText = itemView.findViewById(R.id.textDescriptionNoteText);
               linearLayout = itemView.findViewById(R.id.linearLayoutNote);

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

               Log.d("TAG", "Adapter.getNoteColor: " + note.getColor());

               if(note.getColor() != null){
                    switch (note.getColor()){
                         case "colorDefaultNoteColor":
                              linearLayout.getBackground().setColorFilter(Color.parseColor("#333333"), PorterDuff.Mode.SRC_ATOP);
                              break;
                         case "colorNoteColor2":
                              linearLayout.getBackground().setColorFilter(Color.parseColor("#FDBE3B"), PorterDuff.Mode.SRC_ATOP);
                              break;
                         case "colorNoteColor3":
                              linearLayout.getBackground().setColorFilter(Color.parseColor("#FF4842"), PorterDuff.Mode.SRC_ATOP);
                              break;
                         case "colorNoteColor4":
                              linearLayout.getBackground().setColorFilter(Color.parseColor("#3A52FC"), PorterDuff.Mode.SRC_ATOP);                              break;
                         case "colorNoteColor5":
                              linearLayout.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
                              break;
                    }
               }
          }
     }
}
