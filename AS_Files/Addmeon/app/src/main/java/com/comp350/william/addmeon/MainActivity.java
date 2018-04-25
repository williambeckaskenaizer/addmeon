package com.comp350.william.addmeon;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.MediaStore;

import java.util.List;




public class MainActivity extends AppCompatActivity {
    private AccountDao accountDao;
    private AccountDatabase db;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private AccountViewModel mAccountViewModel;
    public static final int NEW_ACCOUNT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, HomeScreen2.class);
        startActivity(intent);
        finish();
    }


    public void initialLogin(View view) {
        setContentView(R.layout.initial_login_3);

    }

    public void changeImage(View view) {
        ImageButton imgButton = (ImageButton)findViewById(R.id.imageButton);
        imgButton.setBackgroundResource(R.drawable.cheeto);
    }

    public void displayQR(View view) {
        setContentView(R.layout.display_qr_code);
    }

    public void profile(View view) {
        setContentView(R.layout.profile_page);
    }

    //public void goBack(View view) { super.onBackPressed(); }

    public void scan(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    public void profileSave(View view)
    {
        Context context = getApplicationContext();
        CharSequence text = "Saved Changes";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void deleteAccount(View view)
    {
        setContentView(R.layout.delete_profile);
    }

    public void deleteAccountYes(View view)
    {

        setContentView(R.layout.activity_main);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        /*if (requestCode == 0 && resultCode == RESULT_OK)
        {
            setContentView(R.layout.scanned_profile_page);
        }
        else if (requestCode == 1 && resultCode == RESULT_OK)
        {
            setContentView(R.layout.display_qr_code);
        }
        else if(requestCode == 2 && resultCode == RESULT_OK)
        {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        }*/
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == NEW_ACCOUNT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Account account = new Account(intent.getStringExtra(NewAccount.EXTRA_REPLY), intent.getStringExtra(NewAccount.ACCOUNT_TYPE));
            mAccountViewModel.insert(account);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void navigationScanAndGenerate(View view)
    {
        Intent INTENT = new Intent(MainActivity.this, Scan_And_Generate_Popup.class);

        //startActivity(new Intent(MainActivity.this, Scan.class));
        startActivity(INTENT); // working
        //startActivity(new Intent(MainActivity.this, blank.class));
        //setContentView(R.layout.popup_layout);
    }

    public void addSteam(View view)
    {
        // Add Steam to account list
    }

    public void addBattleNet(View view)
    {
        // Add BattleNet to account list
    }

    public void addXboxLive(View view)
    {
        // Add Xbox Live to account list
    }

    public void addPSN(View view)
    {
        // Add PSN to account list
    }

    public void addNintendo(View view)
    {
        // Add Nintendo to account list
    }

    public void addRoblox(View view)
    {
        setContentView(R.layout.add_account);
    }
}
