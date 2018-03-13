package com.comp350.william.addmeon;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Will on 3/12/2018.
 */

public class AccountSpinner extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)


//        if(parent.getItemAtPosition(pos)){
//            //steam
//        }else if(parent.getItemAtPosition(pos)==1){
//            //XBL
//        }else if(parent.getItemAtPosition(pos) == 2){
//            //PSN
//        }else if(parent.getItemAtPosition(pos) == 3) {
//            //Battlenet
//        }else if(parent.getItemAtPosition(pos) == 4){
//            // Nintendo???
//        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
