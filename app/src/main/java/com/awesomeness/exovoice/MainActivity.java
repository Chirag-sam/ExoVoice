package com.awesomeness.exovoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.exotel.voice.Call;
import com.exotel.voice.CallController;
import com.exotel.voice.CallListener;
import com.exotel.voice.ExotelVoiceClient;
import com.exotel.voice.ExotelVoiceClientEventListener;
import com.exotel.voice.ExotelVoiceClientSDK;
import com.exotel.voice.ExotelVoiceError;
import com.exotel.voice.LogLevel;

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

        //String subscriberToken =

        android.content.Context context = this.getApplicationContext();
        exotelVoiceClient.initialize(
                context,		// Android application context
                "https://bellatrix.apac-sg.exotel.in/v1",           // Host URL for the Exotel Voice Platform
                "9980921403",     // Username of subscriber used to generate token
                "9980921403",        // Display name of the subscriber
                "exotel675",         // Exotel Account SID
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJFeG90ZWwiLCJzdWIiOiI5OTgwOTIxNDAzIiwiaWF0IjoiMTY1NTgwNDkwNyIsImV4cCI6IjE2NTU4MDY3MDciLCJjbGllbnRfaWQiOiJleG90ZWw2NzUiLCJkZXZpY2VfaWQiOiJmZDA5ZDlhMTBlMDI4YjQ1Iiwic2NvcGUiOiJ2b2ljZSJ9.hgbXmwlkvBRw6rR4FVjXduaEQ3abNoGhuTohAPciE6s"    // Subscriber token fetched from exotel platform
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