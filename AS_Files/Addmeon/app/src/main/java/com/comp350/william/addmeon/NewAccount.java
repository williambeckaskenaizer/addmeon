package com.comp350.william.addmeon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewAccount extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditAccountView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        mEditAccountView = findViewById(R.id.edit_account);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
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