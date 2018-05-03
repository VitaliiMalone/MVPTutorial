package com.vitaliimalone.mvpvogellatutorial.data;

import com.vitaliimalone.mvpvogellatutorial.data.db.User;

import java.util.List;

public interface UsersDataSource {

    interface GetUsersCallback {

        void onUsersLoaded(List<User> users);
    }

    void getUsers(GetUsersCallback callback);

    void addUser(User user);

    void deleteAllUsers();
}
