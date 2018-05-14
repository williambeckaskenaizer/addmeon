package com.comp350.william.addmeon;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ryan.santos175 on 4/4/18.
 */

public class Generate_Fragment_old extends Fragment {

    public Generate_Fragment_old() {
    }
    Button generate_QRCode;
    ImageView qrCode;
    CircleImageView home_qrCode;
    //CircleImageView profile_qrCode;
    EditText mEditText;
    String urlString = "https://steamcommunity.com/id/reIativity";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generate_scan_fragment, container, false);
        //generate_QRCode = (Button)view.findViewById(R.id.generate_qr);
        qrCode = (ImageView)view.findViewById(R.id.imageView);
        //home_qrCode = (CircleImageView)view.findViewById(R.id.home_circleImageView);
        //profile_qrCode = (CircleImageView)view.findViewById(R.id.profile_circleImageView);

        //mEditText = (EditText)view.findViewById(R.id.editText);

        //String text=mEditText.getText().toString();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(urlString, BarcodeFormat.QR_CODE,400,400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
            //home_qrCode.setImageBitmap(bitmap);
            //profile_qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return view;
    }
}
