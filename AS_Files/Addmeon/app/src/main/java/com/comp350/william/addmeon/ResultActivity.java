package com.comp350.william.addmeon;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResultActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private ConnectionService.RequestApiInterface requestApiInterface;

    private TextView textView;

    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;
    AccountDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        db = AccountDatabase.getDatabase(getApplicationContext());

        textView = (TextView) findViewById(R.id.tv_result);

        // Battlenet rest calls
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

        final Bundle bundle =  this.getIntent().getExtras();
        bnOAuth2Params = bundle.getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(this.prefs, bnOAuth2Params);

        requestApiInterface = ConnectionService.getRequestApiInterface(bnOAuth2Params.getZone());

        final int option = bundle.getInt("option");

        Call<ResponseBody> call = null;
        try {
            switch (option) {
                case Battlenet.REST_BATTLETAG:
                    call = requestApiInterface.getBattlenetProfile(bnOAuth2Helper.getAccessToken());
                    break;
            }

            if (null != call) {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (null != response.body()) {
                                String jsonData = response.body().string();
                                JSONObject Jobject = new JSONObject(jsonData);
                                String Jarray = Jobject.getString("battletag");
                                Log.d("IS THIS THE USERNAME PLEASE?", Jarray);
                                String accountLink = "http://localhost";
                                Account bNetAccount = new Account(Jarray, accountLink, "Battlenet");
                                db.accountDao().insert(bNetAccount);
                                finish();

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException j) {
                            j.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.getLocalizedMessage();
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}