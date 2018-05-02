package com.vitaliimalone.mvpvogellatutorial;

import android.text.TextUtils;

import com.vitaliimalone.mvpvogellatutorial.db.User;

public class UsersPresenter {

    private UsersActivity view;
    private UsersModel model;

    public UsersPresenter(UsersModel model) {
        this.model = model;
    }

    public void attachView(UsersActivity usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        loadUsers();
    }

    public void loadUsers() {
        view.showUsers(model.loadUsers());
    }

    public void add() {
        User user = view.getUserData();
        if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
            view.showToast(R.string.empty_data_error);
        }
        view.showProgress();
        model.addUser(user);
        view.hideProgress();

        loadUsers();
    }

    public void deleteAll() {
        view.showProgress();
        model.deleteUsers();
        view.hideProgress();

        loadUsers();
    }
}
