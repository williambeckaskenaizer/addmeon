package com.comp350.william.addmeon;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class NewAccount extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static String ACCOUNT_TYPE;
    private EditText mEditAccountView;
    private AccountDao accountDao;
    private AccountDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_account);
        final AccountDao accountDao = AccountDatabase.getDatabase(getApplicationContext()).accountDao();


        db = AccountDatabase.getDatabase(NewAccount.this);


        final ImageButton steamButton = findViewById(R.id.steamButton);
        final ImageButton xboxButton = findViewById(R.id.xboxButton);
        final ImageButton battlenetButton = findViewById(R.id.battlenetButton);
        //Add steam account
        steamButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String accountType = "STEAM";
                Intent intent = new Intent(NewAccount.this, Steam.class);
                startActivity(intent);
//                Intent replyIntent = new Intent(NewAccount.this, Steam.class);
//                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

        // Add xbox account
        xboxButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String accountType = "Xbox";
                Intent intent = new Intent(NewAccount.this, Steam.class);
                startActivity(intent);
                Intent replyIntent = new Intent(NewAccount.this, Steam.class);
                Account xboxAccount = new Account("Relativity - " + accountType);
                db.accountDao().insert(xboxAccount);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
        // Add Battlenet account
        battlenetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String accountType = "Battlenet";
                Intent intent = new Intent(NewAccount.this, Steam.class);
                startActivity(intent);
                Intent replyIntent = new Intent(NewAccount.this, Steam.class);
                Account battlenetAccount = new Account("Relativity - " + accountType);
                db.accountDao().insert(battlenetAccount);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }
}
