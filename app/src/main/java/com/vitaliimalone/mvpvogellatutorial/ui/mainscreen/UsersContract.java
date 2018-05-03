package com.vitaliimalone.mvpvogellatutorial.ui.mainscreen;

import com.vitaliimalone.mvpvogellatutorial.data.db.User;

import java.util.List;

public interface UsersContract {

    interface View {

        void setProgressIndicator(boolean active);

        void showUsersList(List<User> users);

        void showEmptyUserError();

    }

    interface Presenter {

        void loadUsers();

        void addNewUser(User user);

        void deleteAllUsers();
    }
}
