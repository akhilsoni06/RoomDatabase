package com.akhil.roomdatabase.activity;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.akhil.roomdatabase.R;
import com.akhil.roomdatabase.db.AppDatabase;
import com.akhil.roomdatabase.model.Note;

/**
 * Created by Akhil on 13-01-2018.
 */

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtTitle;
    private EditText mEdtDescription;
    private AppDatabase mDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        mDataBase = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).build();
        initView();
    }

    private void initView() {
        mEdtTitle = (EditText) findViewById(R.id.edt_title);
        mEdtDescription = (EditText) findViewById(R.id.edt_description);
        findViewById(R.id.btn_add_note).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_note:

                Note note = new Note();

                if (!TextUtils.isEmpty(mEdtTitle.getText().toString().trim()))
                    note.setTitle(mEdtTitle.getText().toString().trim());

                if (!TextUtils.isEmpty(mEdtDescription.getText().toString().trim()))
                    note.setDescription(mEdtDescription.getText().toString().trim());

                new SaveNote().execute(note);
                break;
        }
    }

    private class SaveNote extends AsyncTask<Note, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Note... param) {
            Note saveNote = param[0];
            if (saveNote != null)
                mDataBase.getNoteDao().insetAll(saveNote);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setResult(RESULT_OK);
            finish();
        }
    }
}
