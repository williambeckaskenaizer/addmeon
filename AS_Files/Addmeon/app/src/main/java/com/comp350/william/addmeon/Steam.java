package com.comp350.william.addmeon;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Steam extends Activity {

    // The string will appear to the user in the login screen
    // you can put your app's name
    final String REALM_PARAM = "Addmeon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        webView.setWebViewClient(new WebViewClient()
        {
            @Override

            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                //checks the url being loaded
                setTitle(url);
                Uri Url = Uri.parse(url);

                if(Url.getAuthority().equals(REALM_PARAM.toLowerCase()))
                {
                    // That means that authentication is finished and the url contains user's id.
                    webView.stopLoading();

                    // Extracts user id.
                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    String userId = userAccountUrl.getLastPathSegment();

                    // Do whatever you want with the user's steam id

                }
                setContentView(webView);

                // Constructing openid url request
                String requestedUrl = "https://steamcommunity.com/openid/login?" +
                            "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                            "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                            "openid.mode=checkid_setup&" +
                            "openid.ns=http://specs.openid.net/auth/2.0&" +
                            "openid.realm=https://" + REALM_PARAM + "&" +
                            "openid.return_to=https://" + REALM_PARAM + "/signin/";

                webView.loadUrl(requestedUrl);

            }
        });
    }
}