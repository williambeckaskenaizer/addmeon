package com.comp350.william.addmeon;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import java.util.List;

public class NewAccount extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static String ACCOUNT_TYPE;
    private EditText mEditAccountView;
    private AccountDao accountDao;
    private AccountDatabase db;
    private SharedPreferences sharedPreferences;

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
                String accountLink = "";
                Intent intent = new Intent(NewAccount.this, Steam.class);
                startActivity(intent);
                Intent replyIntent = new Intent(NewAccount.this, Steam.class);
                Account xboxAccount = new Account("Relativity", accountType, accountLink);
                db.accountDao().insert(xboxAccount);
                setResult(RESULT_OK, replyIntent);

                finish();
            }
        });
        // Add Battlenet account
        battlenetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String accountType = "Battlenet";
                String accountLink = "";
                String key = "5v7d8kb32thge54rb6a9qkwncejb2z47";
                String appName = "Addmeon";
                String redirectUrl = "https://localhost";
                BnOAuth2Params bnOAuth2Params = new BnOAuth2Params(key, "Your client secret", BnConstants.ZONE_UNITED_STATES, redirectUrl, appName, "n/a");
                startOauthFlow(bnOAuth2Params);
                finish();
            }
        });
    }
    private void startOauthFlow(final BnOAuth2Params bnOAuth2Params) {
        final Intent intent = new Intent(this, BnOAuthAccessTokenActivity.class);
        // Send BnOAuth2Params
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params);
        // Send redirect Activity
        intent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, Battlenet.class);
        startActivity(intent);
    }
}
