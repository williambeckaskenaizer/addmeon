package com.comp350.william.addmeon;



/**
 * Created by william on 3/26/18.
 */

public class Account {
    String serviceName;
    String accountName;
    boolean isActive;

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
