package com.comp350.william.addmeon;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.net.URL;

public class Steam extends AppCompatActivity {

    // The string will appear to the user in the login screen
    // you can put your app's name

    final String REALM_PARAM = "id";
    AccountDatabase db;
    String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db = AccountDatabase.getDatabase(getApplicationContext());

        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webView.loadUrl("https://steamcommunity.com/login/home/");
        setContentView(webView);


        String requestedUrl = "https://steamcommunity.com/openid/login?" +
        "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
        "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
        "openid.mode=checkid_setup&" +
        "openid.ns=http://specs.openid.net/auth/2.0&" +
        "openid.realm=https://" + REALM_PARAM + "&" +
        "openid.return_to=https://" + REALM_PARAM + "/signin/";

        webView.loadUrl(requestedUrl);


        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                /* This call inject JavaScript into the page which just finished loading. */
                webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                setTitle(url);
                Uri Url = Uri.parse(url);


                if (Url.getAuthority().equals(REALM_PARAM.toLowerCase())) {
                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));

                    String userId = userAccountUrl.getLastPathSegment();

                    webView.loadUrl("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002?key=BC00C8C079B93F8279D259E567145E07&steamids=" + userId + "?xml=1");

                }
            }
        });



        final Activity activity = this;


    }
}




// webView.setWebViewClient(new WebViewClient()
//         {
//@Override
//
//public void onPageStarted(WebView view, String url, Bitmap favicon) {
//
//        //checks the url being loaded
//        setTitle(url);
//        Uri Url = Uri.parse(url);
//
//        if(Url.getAuthority().equals(REALM_PARAM.toLowerCase()))
//        {
//        // That means that authentication is finished and the url contains user's id.
//        webView.stopLoading();
//
//        // Extracts user id.
//        Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
//        String userId = userAccountUrl.getLastPathSegment();
//
//        // Do whatever you want with the user's steam id
//
//        }
//        //setContentView(webView);
//
//        // Constructing openid url request
//        String requestedUrl = "https://steamcommunity.com/openid/login?" +
//        "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
//        "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
//        "openid.mode=checkid_setup&" +
//        "openid.ns=http://specs.openid.net/auth/2.0&" +
//        "openid.realm=https://" + REALM_PARAM + "&" +
//        "openid.return_to=https://" + REALM_PARAM + "/signin/";
//
//        webView.loadUrl(requestedUrl);
//
//        }
//        });

//
//    }
//}