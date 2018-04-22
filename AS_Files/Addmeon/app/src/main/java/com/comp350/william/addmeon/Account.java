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
    private String mAccount;

    @ColumnInfo(name = "type")
    private String mAccountType;

    public String getAccount(){
        return this.mAccount;
    }

    public String getAccountType() {
        return this.mAccountType;
    }

    public Account(@NonNull String account, @NonNull String accountType) {
        this.mAccount = account;
        this.mAccountType = accountType;
    }




}
