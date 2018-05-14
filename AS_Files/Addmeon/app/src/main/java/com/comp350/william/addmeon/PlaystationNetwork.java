package com.comp350.william.addmeon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class PlaystationNetwork extends AppCompatActivity {

    String psnName;
    Button doneButton;
    AccountDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AccountDatabase.getDatabase(getApplicationContext());
        setContentView(R.layout.activity_playstation_network);

        final Button goButton = findViewById(R.id.goButton);


        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        doneButton = new Button(getApplicationContext());
        doneButton.setId(1);
        doneButton.setText("Done");


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ein = (EditText) findViewById(R.id.psn_text);
                psnName = ein.getText().toString();
                webView.loadUrl("https://psnprofiles.com/" + psnName);
                setContentView(webView);
                webView.addView(doneButton);
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account psnAccount = new Account(psnName, "https://psnprofiles.com/" + psnName, "PLAYSTATION");
                db.accountDao().insert(psnAccount);
                finish();
            }
        });
    }
}
