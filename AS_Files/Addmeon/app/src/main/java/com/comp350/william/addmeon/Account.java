package com.comp350.william.addmeon;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by william on 3/26/18.
 */
@Entity(tableName = "account_table")
public class Account {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "account")
    private String mAccountName;
    private String mAccountLink;
    private String mAccountType;


    public String getAccountName(){
        return this.mAccountName;
    }

    public String getAccountLink(){
        return this.mAccountLink;
    }

    public String getAccountType(){
        return this.mAccountType;
    }


    public Account(@NonNull String accountName, @NonNull String accountLink, @NonNull String accountType) {
        this.mAccountName = accountName;
        this.mAccountLink = accountLink;
        this.mAccountType = accountType;
    }






}
