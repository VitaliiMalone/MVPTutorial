package com.vitaliimalone.mvpvogellatutorial.ui.mainscreen;


import com.vitaliimalone.mvpvogellatutorial.data.db.User;
import com.vitaliimalone.mvpvogellatutorial.data.UsersDataSource;

import java.util.List;

public class UsersPresenter implements UsersContract.Presenter {

    private final UsersDataSource usersDataSource;
    private final UsersContract.View usersView;

    public UsersPresenter(UsersDataSource usersDataSource, UsersContract.View usersView) {
        this.usersDataSource = usersDataSource;
        this.usersView = usersView;
    }

    @Override
    public void loadUsers() {
        usersView.setProgressIndicator(true);
        usersDataSource.getUsers(new UsersDataSource.GetUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                usersView.setProgressIndicator(false);
                usersView.showUsersList(users);
            }
        });
    }

    @Override
    public void addNewUser(User user) {
        if (!user.getName().isEmpty()) {
            usersDataSource.addUser(user);
            loadUsers();
        } else {
            usersView.showEmptyUserError();
        }
    }

    @Override
    public void deleteAllUsers() {
        usersDataSource.deleteAllUsers();
        loadUsers();
    }
}
