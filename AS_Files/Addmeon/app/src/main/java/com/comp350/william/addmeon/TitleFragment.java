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


import static com.comp350.william.addmeon.MainActivity.NEW_ACCOUNT_ACTIVITY_REQUEST_CODE;

public class TitleFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.title_page, container, false);
        View overlay = view.findViewById(R.id.title_to_home);

        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        final ImageButton button = view.findViewById(R.id.title_to_home);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startHomeButton();
            }
        });

        return view;
    }

    public void startHomeButton(){
        Fragment fragment;
        fragment = new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }
}
