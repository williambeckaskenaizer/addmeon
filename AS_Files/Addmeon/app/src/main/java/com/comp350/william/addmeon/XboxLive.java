package com.comp350.william.addmeon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class XboxLive extends AppCompatActivity {

    String xboxName;
    Button doneButton;
    AccountDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AccountDatabase.getDatabase(getApplicationContext());
        setContentView(R.layout.activity_xbox_live);

        final Button goButton = findViewById(R.id.goButtonXbox);


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
                EditText ein = (EditText) findViewById(R.id.xbox_text);
                xboxName = ein.getText().toString();
                webView.loadUrl("https://xboxgamertag.com/search/" + xboxName);
                setContentView(webView);
                webView.addView(doneButton);
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account psnAccount = new Account(xboxName, "https://xboxgamertag.com/search/" + xboxName, "XBOX");
                db.accountDao().insert(psnAccount);
                finish();
            }
        });
    }
}
