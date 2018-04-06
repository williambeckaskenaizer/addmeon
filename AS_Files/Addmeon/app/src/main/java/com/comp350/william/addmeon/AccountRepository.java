package com.comp350.william.addmeon;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Will on 4/6/2018.
 */

public class AccountRepository {
    private AccountDao mAccountDao;
    private LiveData<List<Account>> mAllAccounts;

   AccountRepository(Application application) {
        AccountDatabase db = AccountDatabase.getDatabase(application);
        mAccountDao = db.accountDao();
        mAllAccounts = mAccountDao.getAllWords();
    }

    LiveData<List<Account>> getAllAccounts() {
        return mAllAccounts;
    }

    public void insert (Account account) {
        new insertAsyncTask(mAccountDao).execute(account);
    }

    private static class insertAsyncTask extends AsyncTask<Account, Void, Void> {

        private AccountDao mAsyncTaskDao;

        insertAsyncTask(AccountDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Account... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
