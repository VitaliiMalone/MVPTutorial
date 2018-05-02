package com.vitaliimalone.mvpvogellatutorial;

import com.vitaliimalone.mvpvogellatutorial.db.AppDatabase;
import com.vitaliimalone.mvpvogellatutorial.db.User;

import java.util.List;

public class UsersModel {

    private final AppDatabase db;

    public UsersModel(AppDatabase db) {
        this.db = db;
    }

    public List<User> loadUsers() {
        return db.userDao().getAll();
    }

    public void addUser(User user) {
        db.userDao().insert(user);
    }

    public void deleteUsers() {
        db.userDao().deleteAll();
    }
}
