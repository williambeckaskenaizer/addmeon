package com.comp350.william.addmeon;



/**
 * Created by william on 3/26/18.
 */

public class Account {
    String serviceName;
    String accountName;
    boolean isActive;

    public void SetAccountName(String name){
        this.accountName = name;
    }
    public void DefineService(String service){
        this.serviceName = service;
    }

    public void SetActiveState(boolean state){
        this.isActive = state;
    }
}
