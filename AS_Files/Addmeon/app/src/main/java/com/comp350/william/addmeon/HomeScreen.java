package com.comp350.william.addmeon;






import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.List;

import static com.comp350.william.addmeon.MainActivity.NEW_ACCOUNT_ACTIVITY_REQUEST_CODE;

public class HomeScreen extends AppCompatActivity {
    private AccountDao accountDao;
    private AccountDatabase db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private AccountViewModel mAccountViewModel;
    private ActionBar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setupNavigationView();


        db = AccountDatabase.getDatabase(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        mAccountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);

        //Nav bar stuff
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Addmeon");
        loadFragment(new HomeFragment());
        // end nav bar stuff


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AccountListAdapter adapter = new AccountListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAccountViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable final List<Account> accounts) {
                // Update the cached copy of the words in the adapter.
                adapter.setAccounts(accounts);
            }
        });

        final ImageButton button = findViewById(R.id.addAccountButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addAccountButton();
            }
        });

        Button nukeButton = findViewById(R.id.nukeAccountListButton);
        nukeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nukeAccountListButton();
            }
        });

    }

    private void setupNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.constraint_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.menu_home:
                    toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.menu_scan:
                    toolbar.setTitle("Scan");
                    fragment = new Generate_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.menu_profile:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };


    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.menu_home, fragment);
                ft.commit();
            }
        }
    }

    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.menu_home:
                // Action to perform when Home Menu item is selected.
                pushFragment(new HomeFragment());
                break;
            case R.id.menu_profile:
                // Action to perform when Account Menu item is selected.
                pushFragment(new ProfileFragment());
                break;
        }
    }

    public void addAccountButton(){
        Intent intent = new Intent(HomeScreen.this, NewAccount.class);
        startActivityForResult(intent, NEW_ACCOUNT_ACTIVITY_REQUEST_CODE);
    }

    public void navigationScanAndGenerate(View view)
    {
        Intent INTENT = new Intent(HomeScreen.this, Scan_And_Generate_Popup.class);
        startActivity(INTENT); // working

    }

//    public void profileButton(View view){
//        setContentView(R.layout.profile_page);
//    }

    public void nukeAccountListButton(){
        db.accountDao().nukeAccountList();
    }


}
