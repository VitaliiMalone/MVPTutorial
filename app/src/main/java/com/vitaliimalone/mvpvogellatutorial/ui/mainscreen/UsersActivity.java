package com.vitaliimalone.mvpvogellatutorial.ui.mainscreen;

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
import com.vitaliimalone.mvpvogellatutorial.data.db.AppDatabase;
import com.vitaliimalone.mvpvogellatutorial.data.db.User;
import com.vitaliimalone.mvpvogellatutorial.data.UsersDataSource;
import com.vitaliimalone.mvpvogellatutorial.data.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity implements UsersContract.View {

    private UsersContract.Presenter presenter;

    private UsersAdapter usersAdapter;

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

        AppDatabase db = AppDatabase.getAppDatabase(this);
        UsersDataSource dataSource = new UsersRepository(db);
        presenter = new UsersPresenter(dataSource, this);

        nameEt = findViewById(R.id.name_et);
        emailEt = findViewById(R.id.email_et);
        addButton = findViewById(R.id.add_button);
        deleteAllButton = findViewById(R.id.delete_all_button);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(nameEt.getText().toString());
                user.setEmail(emailEt.getText().toString());
                presenter.addNewUser(user);
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteAllUsers();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter = new UsersAdapter(new ArrayList<User>());
        recyclerView.setAdapter(usersAdapter);

        presenter.loadUsers();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if (active) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showUsersList(List<User> users) {
        usersAdapter.replaceData(users);
    }

    @Override
    public void showEmptyUserError() {
        Toast.makeText(this, R.string.empty_data_error, Toast.LENGTH_SHORT).show();
    }
}
