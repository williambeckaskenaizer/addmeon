package com.comp350.william.addmeon;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.Color;

/**
 * Created by Will on 4/1/2018.
 */

public class AccountManager extends Activity implements AdapterView.OnItemSelectedListener{
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    public ArrayList<Account> accountList = new ArrayList<>();

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos);

        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.listView );

        // Create and populate a List of platforms.
        String[] platforms = new String[] { "Steam",
                "Xbox Live",
                "Playstation Network",
                "BattleNet",
                "Nintendo"};
        ArrayList<String> platformList = new ArrayList<>();
        platformList.addAll( Arrays.asList(platforms) );

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.home_screen, platformList);

        // Add more platforms. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.

        if(pos == 0){
            //steam

            /*
            public class LoginActivity extends ActionBarActivity {

                // The string will appear to the user in the login screen
                // you can put your app's name
                final String REALM_PARAM = "YourAppName";

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

                            });
                            setContentView(webView);

                            // Constructing openid url request
                            String url = "https://steamcommunity.com/openid/login?" +
                                    "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                                    "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                                    "openid.mode=checkid_setup&" +
                                    "openid.ns=http://specs.openid.net/auth/2.0&" +
                                    "openid.realm=https://" + REALM_PARAM + "&" +
                                    "openid.return_to=https://" + REALM_PARAM + "/signin/";

                            webView.loadUrl(url);

                        }
                    }
            */
            
            listAdapter.add("Steam");
        }else if(pos == 1){
            //XBL
            listAdapter.add( "Xbox Live" );
        }else if(pos == 2){
            //PSN
            listAdapter.add( "Playstation Network" );
        }else if(pos == 3) {
            //Battlenet
            listAdapter.add( "BattleNet" );
        }else if(pos == 4){
            // Nintendo???
            listAdapter.add( "Nintendo" );
        }

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public Account getAccountFromSpinner(){
        Account account = new Account();
        return account;
    }
    public void addAccountToList(Account account){
        accountList.add(account);
    }

}
/*
            Account steam = new Account();
            steam.setAccountName("Testing Steam");
            steam.setActiveState(true);
            steam.setServiceName("Steam");
            addAccountToList(steam);
 */
