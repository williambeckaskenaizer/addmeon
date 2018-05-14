package com.comp350.william.addmeon;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ryan.santos175 on 4/4/18.
 */

public class Generate_Fragment extends Fragment {

    public Generate_Fragment() {
    }
    private List<Account> mAccounts;
    private AccountViewModel mAccountViewModel;
    AccountDatabase db;
    Button generate_QRCode;
    ImageView qrCode;
    CircleImageView home_qrCode;
    //CircleImageView profile_qrCode;
    EditText mEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String encodeString = "";
        View view = inflater.inflate(R.layout.generate_scan_fragment, container, false);
        qrCode = (ImageView)view.findViewById(R.id.imageView);

        final AccountListAdapter adapter = new AccountListAdapter(getContext());

        mAccountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);

        db = AccountDatabase.getDatabase(view.getContext());

        System.out.println(mAccountViewModel.getAllAccounts().toString());

        /*encodeString += adapter.getAllAccountStrings();

        if(encodeString == "")
        {
            Toast.makeText(getContext(), "No Accounts", Toast.LENGTH_LONG).show();
            System.out.println("hit");
            return view;
        }*/

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(db.accountDao().getAllAccounts().toString(), BarcodeFormat.QR_CODE,400,400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return view;
    }

    public String getAccountString()
    {
        //System.out.println(encodeString);

        String encode = "";

        /*setAccounts(mAccounts);

        for(int i = 0; i < 4; i++)
        {
            encode += getAccount(i);
        }*/

        return encode;
    }

    /*void setAccounts(List<Account> accounts){
        mAccounts = accounts;
        AccountListAdapter test = new AccountListAdapter(getContext());
        test.notifyDataSetChanged();
    }

    public String getAccount(int position) {

        Account current = mAccounts.get(position);

        String account;

        if(current.getAccountType() == null || current.getAccountName() == null || current.getAccountLink() == null)
        {
            return null;
        }

        account = current.getAccountType() + "/" + current.getAccountName() + "/" + current.getAccountLink() + "/";

        return account;
    }*/
}
