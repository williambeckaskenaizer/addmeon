package com.comp350.william.addmeon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by ryan.santos175 on 4/4/18.
 */

public class Generate_Fragment extends Fragment {

    public Generate_Fragment() {
    }
    Button generate_QRCode;
    ImageView qrCode;
    EditText mEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generate_scan_fragment, container, false);
        generate_QRCode = (Button)view.findViewById(R.id.generate_qr);
        qrCode = (ImageView)view.findViewById(R.id.imageView);
        mEditText = (EditText)view.findViewById(R.id.editText);
        generate_QRCode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text=mEditText.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,400,400);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrCode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
