package com.comp350.william.addmeon;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import static com.comp350.william.addmeon.MainActivity.NEW_ACCOUNT_ACTIVITY_REQUEST_CODE;

public class HomeScreen extends AppCompatActivity {
    private AccountDao accountDao;
    private AccountDatabase db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private AccountViewModel mAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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

        Button nukeButton = findViewById(R.id.nukeAccountListButton);
        nukeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nukeAccountListButton();
            }
        });
    }
    public void addAccountButton(){
        Intent intent = new Intent(HomeScreen.this, NewAccount.class);
        startActivityForResult(intent, NEW_ACCOUNT_ACTIVITY_REQUEST_CODE);
    }

    public void navigationScanAndGenerate(View view)
    {
        Intent INTENT = new Intent(HomeScreen.this, Scan_And_Generate_Popup.class);
        startActivity(INTENT); // working

    }

    public void nukeAccountListButton(){
        db.accountDao().nukeAccountList();
    }


}
