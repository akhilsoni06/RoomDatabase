package com.akhil.roomdatabase.activity;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.akhil.roomdatabase.R;
import com.akhil.roomdatabase.db.AppDatabase;
import com.akhil.roomdatabase.model.User;

/**
 * Created by Akhil on 13-01-2018.
 */

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtName;
    private EditText mEdtCity;
    private AppDatabase mDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_activity);
        mDataBase = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).build();
        initView();
    }

    private void initView() {
        mEdtName = (EditText) findViewById(R.id.edt_name);
        mEdtCity = (EditText) findViewById(R.id.edt_city);
        findViewById(R.id.btn_add_user).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_user:

                User user = new User();

                if (!TextUtils.isEmpty(mEdtName.getText().toString().trim()))
                    user.setName(mEdtName.getText().toString().trim());

                if (!TextUtils.isEmpty(mEdtCity.getText().toString().trim()))
                    user.setCity(mEdtCity.getText().toString().trim());

                new SaveUser().execute(user);
                break;
        }
    }

    private class SaveUser extends AsyncTask<User, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(User... param) {
            User saveUser = param[0];
            if (saveUser != null)
                mDataBase.getUserDao().insetAll(saveUser);
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
