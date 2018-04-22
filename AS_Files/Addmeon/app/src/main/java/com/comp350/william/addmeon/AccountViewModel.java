package com.comp350.william.addmeon;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Will on 4/6/2018.
 */

public class AccountViewModel extends AndroidViewModel {

    private AccountRepository mRepository;

    private LiveData<List<Account>> mAllAccounts;

    public AccountViewModel (Application application) {
        super(application);
        mRepository = new AccountRepository(application);
        mAllAccounts = mRepository.getAllAccounts();
    }
    LiveData<List<Account>> getAllAccounts() { return mAllAccounts; }

    public void insert(Account account) {
        mRepository.insert(account);
    }
}
