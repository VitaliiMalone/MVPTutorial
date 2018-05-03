package com.vitaliimalone.mvpvogellatutorial.ui.mainscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitaliimalone.mvpvogellatutorial.R;
import com.vitaliimalone.mvpvogellatutorial.data.db.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> users;

    public UsersAdapter(List<User> users) {
        setList(users);
    }

    private void setList(List<User> users) {
        this.users = users;
    }

    public void replaceData(List<User> users) {
        setList(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        String textToShow = users.get(position).getName() + " " + users.get(position).getEmail();
        holder.userInfoTv.setText(textToShow);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userInfoTv;

        public UserViewHolder(View itemView) {
            super(itemView);

            userInfoTv = itemView.findViewById(R.id.user_info_item_tv);
        }
    }
}
