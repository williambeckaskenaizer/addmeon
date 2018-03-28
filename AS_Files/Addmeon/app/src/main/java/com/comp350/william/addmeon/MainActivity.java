package com.comp350.william.addmeon;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

/*
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.platform_arrays, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/
    }

    public void initialLogin(View view) {
        setContentView(R.layout.initial_login_3);
//        Button button = (Button) view;
//        ((Button) view).setText("clicked");
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);

    }

    public void changeImage(View view) {
        ImageButton imgButton = (ImageButton)findViewById(R.id.imageButton);
        imgButton.setBackgroundResource(R.drawable.cheeto);
    }

    public void homeScreen(View view) {

        setContentView(R.layout.home_screen);


    }

    public void addAccount(View view) {
        setContentView(R.layout.add_account);
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
        if (requestCode == 0 && resultCode == RESULT_OK)
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
        }
    }

    public void navigation_scan(View view)
    {
        //startActivityForResult(new Intent(MainActivity.this,Scan_Popup.class),1);
        setContentView(R.layout.popup_layout);
    }



}
