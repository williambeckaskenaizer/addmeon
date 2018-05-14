package com.comp350.william.addmeon;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Will on 4/6/2018.
 */

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountViewHolder> implements View.OnClickListener {

    @Override
    public void onClick(View view) {

    }


    class AccountViewHolder extends RecyclerView.ViewHolder {
        protected final TextView accountItemView;
        protected ImageView serviceIcon;
        protected final TextView accountServiceName;
        protected final TextView accountLink;

        private AccountViewHolder(View itemView) {
            super(itemView);
            accountItemView = itemView.findViewById(R.id.title);
            accountServiceName = itemView.findViewById(R.id.year);
            accountLink = itemView.findViewById(R.id.genre);

            //serviceIcon = (ImageView) itemView.findViewById(R.id.itemIcon);
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
            holder.accountServiceName.setText(current.getAccountType());
            holder.accountLink.setText(current.getAccountLink());


            if(current.getAccountType().equals("BATTLENET")){
                holder.accountServiceName.setTextColor(Color.parseColor("#3498DB"));
            }
            if(current.getAccountType().equals("STEAM")){
                holder.accountServiceName.setTextColor(Color.GRAY);
            }
            if(current.getAccountType().equals("PLAYSTATION")){
                holder.accountServiceName.setTextColor(Color.CYAN);
            }
            if(current.getAccountType().equals("XBOX")){
                holder.accountServiceName.setTextColor(Color.GREEN);
            }

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

