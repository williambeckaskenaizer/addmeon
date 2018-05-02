package com.comp350.william.addmeon;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    AccountDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        db = AccountDatabase.getDatabase(view.getContext());

        final ImageButton eraseButton = view.findViewById(R.id.eraseAllData);
        eraseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Erase all data?")
                        .setMessage("This will remove all the accounts you've added.")
                        .setPositiveButton(android.R.string.ok, null)
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });

        CircleImageView image = view.findViewById(R.id.circleImageView);
        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generate_display_qr();
            }
        });

        return view;
    }

    public void generate_display_qr(){
        Intent INTENT = new Intent(getActivity(), Generate.class);
        startActivity(INTENT);
    }

}

