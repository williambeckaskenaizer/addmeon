package com.comp350.william.addmeon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Ryan on 3/13/2018.
 */

public class Scan_And_Generate_Popup extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        //getWindow().setLayout((int)(width*.8),(int)(height*.5));

        getWindow().setLayout((int)(width*.7),(int)(height*.3));

        setFinishOnTouchOutside(true);
    }

    public void displayQR(View view) {
        //setContentView(R.layout.display_qr_code);

        startActivity(new Intent(Scan_And_Generate_Popup.this, Generate.class));

        finish();
    }

    public void scan(View view) {
        startActivity(new Intent(Scan_And_Generate_Popup.this, Scan.class));

        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0 && resultCode == RESULT_OK)
        {
            finishActivity(2);
        }
    }
}
