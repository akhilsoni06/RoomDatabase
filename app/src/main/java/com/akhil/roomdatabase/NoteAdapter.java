package com.akhil.roomdatabase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.akhil.roomdatabase.model.Note;

import java.util.List;

/**
 * Created by Akhil on 13-01-2018.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ItemHolder> {

    private List<Note> mNoteList;

    public NoteAdapter(List<Note> list) {
        mNoteList = list;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

        Note note = mNoteList.get(position);
        holder.mTitle.setText(note.getTitle());
        holder.mDescription.setText(note.getDescription());

    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;

        public ItemHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDescription = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
