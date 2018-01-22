package com.akhil.roomdatabase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akhil.roomdatabase.R;
import com.akhil.roomdatabase.model.User;

import java.util.List;

/**
 * Created by Akhil on 13-01-2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemHolder> {

    private List<User> mUserList;
    public UserAdapter(List<User> list) {
        mUserList = list;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        User user = mUserList.get(position);
        holder.mUserName.setText(user.getName());
        holder.mUserCity.setText(user.getCity());
    }

    public User getUser(int position) {
        return mUserList.get(position);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mUserName;
        private TextView mUserCity;

        public ItemHolder(View itemView) {
            super(itemView);
            mUserName = (TextView) itemView.findViewById(R.id.name);
            mUserCity = (TextView) itemView.findViewById(R.id.city);
        }
    }
}
