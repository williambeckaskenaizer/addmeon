package com.comp350.william.addmeon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Battlenet extends AppCompatActivity{

    AccountDatabase db;
    String urlString;
    String userId;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db = AccountDatabase.getDatabase(getApplicationContext());




        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webView.loadUrl("https://steamcommunity.com/login/home/");
        setContentView(webView);


        String requestedUrl = "https://us.battle.net/oauth/authorize";
        webView.loadUrl(requestedUrl);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        //"https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002?key=BC00C8C079B93F8279D259E567145E07&steamids=" + userId
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                /* This call inject JavaScript into the page which just finished loading. */
                Uri Url = Uri.parse(url);

            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                setTitle(url);
                Uri Url = Uri.parse(url);


                    try {
                        URL fuckingURL = new URL("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/"
                                + "?key="
                                + "&steamids=" + userId);

                        // Connect to the URL using java's native library
                        HttpURLConnection request = (HttpURLConnection) fuckingURL.openConnection();
                        request.connect();

                        // Convert to a JSON object to print data
//                        JsonParser jp = new JsonParser(); //from gson
//                        InputStreamReader stream = new InputStreamReader((InputStream) request.getContent());
//                        JsonObject root = jp.parse(stream).getAsJsonObject();
//                        JsonObject response = root.get("response").getAsJsonObject();
//                        JsonObject player = response.get("players").getAsJsonArray().get(0).getAsJsonObject();

//                        Account steamAccount = new Account(player.get("personaname").getAsString(), userAccountUrl.toString(), accountType);
//                        Log.d("User Account URL", userAccountUrl.toString());
                        //db.accountDao().insert(steamAccount);
                        //finish();
                    } catch (IOException e){
                        throw new RuntimeException(e);
                    }

                    //webView.loadUrl("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002?key=BC00C8C079B93F8279D259E567145E07&steamids=" + userId);

            }
        });


    }
}

