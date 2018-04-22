package com.comp350.william.addmeon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewAccount extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static String ACCOUNT_TYPE;
    private EditText mEditAccountView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_account);
        mEditAccountView = findViewById(R.id.edit_account);

        final ImageButton steamButton = findViewById(R.id.steamButton);
        final ImageButton xboxButton = findViewById(R.id.xboxButton);
        final ImageButton battlenetButton = findViewById(R.id.battlenetButton);
        //Add steam account
        steamButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ACCOUNT_TYPE = "Steam";
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditAccountView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String account = mEditAccountView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, account);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        // Add xbox account
        xboxButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditAccountView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String account = mEditAccountView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, account);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
        // Add Battlenet account
        battlenetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditAccountView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String account = mEditAccountView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, account);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
