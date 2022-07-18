package com.awesomeness.exovoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.exotel.voice.Call;
import com.exotel.voice.CallController;
import com.exotel.voice.CallListener;
import com.exotel.voice.ExotelVoiceClient;
import com.exotel.voice.ExotelVoiceClientEventListener;
import com.exotel.voice.ExotelVoiceClientSDK;
import com.exotel.voice.ExotelVoiceError;
import com.exotel.voice.LogLevel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements ExotelVoiceClientEventListener, CallListener {

    Button makeOutgoingCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeOutgoingCall = findViewById(R.id.make_outgoing_call);

        // Get Exotel Voice Client
        ExotelVoiceClient exotelVoiceClient = ExotelVoiceClientSDK.getExotelVoiceClient();

        exotelVoiceClient.setEventListener(this);

        //String subscriberToken =

        android.content.Context context = this.getApplicationContext();
        exotelVoiceClient.initialize(
                context,		// Android application context
                "https://bellatrix.apac-sg.exotel.in/v1",           // Host URL for the Exotel Voice Platform
                "9980921403",     // Username of subscriber used to generate token
                "9980921403",        // Display name of the subscriber
                "exotel675",         // Exotel Account SID
                "{\n" +
                        "        \"refresh_token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJFeG90ZWwiLCJzdWIiOiI5OTgwOTIxNDAzIiwiaWF0IjoiMTY1NjQ5NjIxMyIsImV4cCI6IjE2NTY1ODI2MTMiLCJjbGllbnRfaWQiOiJleG90ZWw2NzUiLCJkZXZpY2VfaWQiOiJmZDA5ZDlhMTBlMDI4YjQ1In0.6UdAVKJ8mlBkSnasMo5vG0a0cxEbejvsszQ2PlwzTb4\",\n" +
                        "        \"access_token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJFeG90ZWwiLCJzdWIiOiI5OTgwOTIxNDAzIiwiaWF0IjoiMTY1NjQ5NjIxMyIsImV4cCI6IjE2NTY0OTgwMTMiLCJjbGllbnRfaWQiOiJleG90ZWw2NzUiLCJkZXZpY2VfaWQiOiJmZDA5ZDlhMTBlMDI4YjQ1Iiwic2NvcGUiOiJ2b2ljZSJ9.5Wfa1TF7WiQDC0ToR2qiQZO4tn2DOQKnMJAvijENLtM\"\n" +
                        "    }"    // Subscriber token fetched from exotel platform
        );

        // Get Call Controller interface
        CallController callController = exotelVoiceClient.getCallController();

        makeOutgoingCall.setOnClickListener(view -> {
            try {
                Call call = callController.dial("+918754392544");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("TAG", "onCreate: Android ID- "+androidId);
        AndroidNetworking.post("https://bellatrix.apac-sg.exotel.in/v1/login")
                .addBodyParameter("user_name", "9980921403")
                .addBodyParameter("password", "exotel321")
                .addBodyParameter("account_sid", "exotel675")
                .addBodyParameter("device_id", androidId)
                .addBodyParameter("platform", "android")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("TAGGGGG", "onResponse: "+response.toString());
                            Log.d("TAG", "onResponse: Got the response!!1");
                            Log.d("TAG", "onResponse: "+response.getString("subscriber_name"));
                        } catch (JSONException e) {
                            Log.d("TAG", "onResponsee: "+e.getLocalizedMessage());
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d("TAG", "Error! onResponse: "+error.getErrorBody());
                        // handle error
                    }
                });




    }

    @Override
    public void onInitializationSuccess() {
        Log.d("TAG", "onInitializationSuccess: SDK Initialized successfully");
    }

    @Override
    public void onInitializationFailure(ExotelVoiceError exotelVoiceError) {
        Log.d("TAG", "onInitializationFailure: "+exotelVoiceError.getErrorMessage());
    }

    @Override
    public void onLog(LogLevel logLevel, String s, String s1) {
        Log.d("TAG", "onLog: "+s);
    }

    @Override
    public void onUploadLogSuccess() {

    }

    @Override
    public void onUploadLogFailure(ExotelVoiceError exotelVoiceError) {

    }

    @Override
    public void onAuthenticationFailure(ExotelVoiceError exotelVoiceError) {
        Log.d("TAG", "onAuthenticationFailure: "+exotelVoiceError.getErrorMessage());
    }


    @Override
    public void onIncomingCall(Call call) {

    }

    @Override
    public void onCallInitiated(Call call) {
        Log.d("TAG", "onCallInitiated: "+call.getCallDetails());
    }

    @Override
    public void onCallRinging(Call call) {
        Log.d("TAG", "onCallRinging: ");

    }

    @Override
    public void onCallEstablished(Call call) {

    }

    @Override
    public void onCallEnded(Call call) {

    }

    @Override
    public void onMissedCall(String s, Date date) {

    }
}