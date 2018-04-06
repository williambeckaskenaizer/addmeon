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
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "account")
    String serviceName;
    String accountName;
    boolean isActive;
    private int id;

    public String getAccount(){
        return this.accountName;
    }
    public void setAccountName(String name){
        this.accountName = name;
    }
    public void setServiceName(String service){
        this.serviceName = service;
    }

    public void setActiveState(boolean state){
        this.isActive = state;
    }

}
