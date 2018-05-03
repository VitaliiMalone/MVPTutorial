package com.vitaliimalone.mvpvogellatutorial.data;

import com.vitaliimalone.mvpvogellatutorial.data.db.AppDatabase;
import com.vitaliimalone.mvpvogellatutorial.data.db.User;

import java.util.List;

public class UsersRepository implements UsersDataSource {

    private AppDatabase db;

    public UsersRepository(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void getUsers(GetUsersCallback callback) {
        List<User> users = db.userDao().getAll();
        callback.onUsersLoaded(users);
    }

    @Override
    public void addUser(User user) {
        db.userDao().insert(user);
    }

    @Override
    public void deleteAllUsers() {
        db.userDao().deleteAll();
    }
}
