package com.comp350.william.addmeon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Will on 4/6/2018.
 */

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountViewHolder> {

    class AccountViewHolder extends RecyclerView.ViewHolder {
        private final TextView accountItemView;
        private ImageView serviceIcon;

        private AccountViewHolder(View itemView) {
            super(itemView);
            accountItemView = itemView.findViewById(R.id.textView);

            serviceIcon = (ImageView) itemView.findViewById(R.id.steam_icon);
        }
    }

    private final LayoutInflater mInflater;
    private List<Account> mAccounts; // Cached copy of accounts

    AccountListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new AccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        if (mAccounts != null) {
            Account current = mAccounts.get(position);
            holder.accountItemView.setText(current.getAccountName());
        } else {
            // Covers the case of data not being ready yet.
            holder.accountItemView.setText("No Accounts");
        }
    }

    void setAccounts(List<Account> accounts){
        mAccounts = accounts;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mAccounts has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAccounts != null)
            return mAccounts.size();
        else return 0;
    }
}

