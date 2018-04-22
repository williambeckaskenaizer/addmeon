package com.comp350.william.addmeon;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by william on 4/4/18.
 */
@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account account);

    @Query("DELETE FROM account_table")
    void nukeAccountList();

    @Query("SELECT * from account_table ORDER BY account ASC")
    LiveData<List<Account>> getAllAccounts();
}
