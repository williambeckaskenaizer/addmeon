package com.comp350.william.addmeon;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Arrays;
import java.util.List;


/**
 * Created by ryan.santos175 on 4/16/18.
 */

public class Scan extends AppCompatActivity {

    /*List<Account> mAccounts;

    final ImageButton s_button = findViewById(R.id.GO_steam);
    final ImageButton b_button = findViewById(R.id.GO_battlenet);
    final ImageButton x_button = findViewById(R.id.GO_xbox);
    final ImageButton p_button = findViewById(R.id.GO_psn);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan QR Code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //AccountDatabase scanned_db = new AccountDatabase();
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String[] list;
        String info = "";
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Log.d("MainActivity", "Scanned");
                //TextView scanResult = (TextView)findViewById(R.id.scanned_text_view_id);
                //scanResult.setText(result.getContents());
                //setAccounts(result.getContents());

                //TextView scanresult = (TextView)findViewById(R.id.scanned_string);
                //list = result.getContents().split(">");
                //scanresult.setText(Arrays.toString(list));

                setContentView(R.layout.scanned_profile);

                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void go_to_steam()
    {
        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://steamcommunity.com/profile/76561198042157467");
        setContentView(webView);
    }


    /*void setAccounts(List<Account> accounts){
        mAccounts = accounts;
        //notifyDataSetChanged();
    }*/
}
