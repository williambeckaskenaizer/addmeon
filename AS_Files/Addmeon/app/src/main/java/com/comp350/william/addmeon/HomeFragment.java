package com.comp350.william.addmeon;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.comp350.william.addmeon.MainActivity.NEW_ACCOUNT_ACTIVITY_REQUEST_CODE;

public class HomeFragment extends Fragment {

    private AccountDao accountDao;
    private AccountDatabase db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private AccountViewModel mAccountViewModel;
    private ActionBar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_screen, container, false);

        db = AccountDatabase.getDatabase(view.getContext());
        super.onCreate(savedInstanceState);
        mAccountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final AccountListAdapter adapter = new AccountListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter( adapter );

        mAccountViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable final List<Account> accounts) {
                // Update the cached copy of the words in the adapter.
                adapter.setAccounts(accounts);
            }
        });

        final ImageButton button = view.findViewById(R.id.addAccountButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addAccountButton();
            }
        });

        CircleImageView image = view.findViewById(R.id.circleImageView);
        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generate_display_qr();
            }
        });

        Button nukeButton = view.findViewById(R.id.nukeAccountListButton);
        nukeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nukeAccountListButton();
            }
        });
        return view;

    }

    public void addAccountButton(){
        Intent intent = new Intent(getActivity(), NewAccount.class);
        startActivityForResult(intent, NEW_ACCOUNT_ACTIVITY_REQUEST_CODE);
    }

    public void nukeAccountListButton(){
        db.accountDao().nukeAccountList();
    }

    public void generate_display_qr(){
        Intent INTENT = new Intent(getActivity(), Generate.class);
        startActivity(INTENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

}


