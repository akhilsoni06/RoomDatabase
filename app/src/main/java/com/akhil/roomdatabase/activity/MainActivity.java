package com.akhil.roomdatabase.activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.akhil.roomdatabase.R;
import com.akhil.roomdatabase.adapter.NoteAdapter;
import com.akhil.roomdatabase.db.AppDatabase;
import com.akhil.roomdatabase.model.Note;

import java.util.List;

/**
 * Created by Akhil on 12-01-2018.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecycleListView;
    private AppDatabase mDataBase;
    private NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBase = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).build();
        initView();
    }

    private void initView() {
        mRecycleListView = (RecyclerView) findViewById(R.id.recycler_view);
        findViewById(R.id.add).setOnClickListener(this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        mRecycleListView.setLayoutManager(manager);

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteNote(mNoteAdapter.getNote(viewHolder.getAdapterPosition()));
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecycleListView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add) {
            startActivity(new Intent(this, AddNoteActivity.class));
        }
    }

    private void loadNotes() {
        new AsyncTask<Void, Void, List<Note>>() {
            @Override
            protected List<Note> doInBackground(Void... params) {
                return mDataBase.getNoteDao().getAllNote();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                mNoteAdapter = new NoteAdapter(notes);
                mRecycleListView.setAdapter(mNoteAdapter);
            }
        }.execute();
    }


    private void deleteNote(final Note note) {
        new AsyncTask<Note, Void, Void>() {
            @Override
            protected Void doInBackground(Note... notes) {
                mDataBase.getNoteDao().deleteAll(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                loadNotes();
            }
        }.execute(note);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataBase.close();
    }
}
