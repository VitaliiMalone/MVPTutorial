package com.vitaliimalone.mvpvogellatutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vitaliimalone.mvpvogellatutorial.R;
import com.vitaliimalone.mvpvogellatutorial.db.AppDatabase;
import com.vitaliimalone.mvpvogellatutorial.db.User;

import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private UsersAdapter usersAdapter;

    private UsersPresenter presenter;

    private EditText nameEt;
    private EditText emailEt;
    private Button addButton;
    private Button deleteAllButton;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = findViewById(R.id.name_et);
        emailEt = findViewById(R.id.email_et);
        addButton = findViewById(R.id.add_button);
        deleteAllButton = findViewById(R.id.delete_all_button);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.add();
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteAll();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = AppDatabase.getAppDatabase(this);
        UsersModel usersModel = new UsersModel(db);
        presenter = new UsersPresenter(usersModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public User getUserData() {
        User user = new User();
        user.setName(nameEt.getText().toString());
        user.setEmail(emailEt.getText().toString());
        return user;
    }

    public void showUsers(List<User> users) {
        recyclerView.setAdapter(new UsersAdapter(users));
    }

    public void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
