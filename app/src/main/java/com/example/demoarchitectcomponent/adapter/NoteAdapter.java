package com.example.demoarchitectcomponent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoarchitectcomponent.R;
import com.example.demoarchitectcomponent.note.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> allNotes = new ArrayList<>();

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = allNotes.get(position);
        holder.titleTv.setText(currentNote.getTitle());
        holder.descTv.setText(currentNote.getDescription());
        holder.priorityTv.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void setAllNotes(List<Note> notes) {
        this.allNotes = notes;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView priorityTv, titleTv, descTv;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            priorityTv = (TextView) itemView.findViewById(R.id.priority_tv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            descTv = (TextView) itemView.findViewById(R.id.description_tv);
        }
    }
}
