package com.comp350.william.addmeon;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import static com.comp350.william.addmeon.MainActivity.NEW_ACCOUNT_ACTIVITY_REQUEST_CODE;

public class HomeScreen2 extends AppCompatActivity {

    private TextView mTextMessage;
    private AccountDao accountDao;
    private AccountDatabase db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private AccountViewModel mAccountViewModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("Home");
                    startActivity(new Intent(HomeScreen2.this, HomeScreen2.class));
                case R.id.navigation_dashboard:
                    mTextMessage.setText("Scan");
                    startActivity(new Intent(HomeScreen2.this, Scan_And_Generate_Popup.class));
                case R.id.navigation_notifications:
                    mTextMessage.setText("Profile");
                    setContentView(R.layout.profile_page);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setContentView(R.layout.home_screen);
        mAccountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AccountListAdapter adapter = new AccountListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAccountViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable final List<Account> accounts) {
                // Update the cached copy of the words in the adapter.
                adapter.setAccounts(accounts);
            }
        });
        final ImageButton button = findViewById(R.id.addAccountButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addAccountButton();
            }
        });
    }
    public void addAccountButton(){
        Intent intent = new Intent(HomeScreen2.this, NewAccount.class);
        startActivityForResult(intent, NEW_ACCOUNT_ACTIVITY_REQUEST_CODE);
    }

}
