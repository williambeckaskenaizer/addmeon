package com.comp350.william.addmeon;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        ArrayList<String> platformList = new ArrayList<String>();
        platformList.addAll( Arrays.asList(platforms) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.home_screen, platformList);

        // Add more platforms. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.

        if(pos == 0){
            //steam
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
